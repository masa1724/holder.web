package holder.infra.log;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Aspect
@Component 
public class TraceLog {
    private static final Logger logger = 
            LoggerFactory.getLogger(TraceLog.class);
 
    /*
     * execution(メソッドの修飾子 メソッドの戻り値 パッケージ.クラス(インターフェース).メソッド名(引数の型, ...) throws 例外
     * 省略可 ： 「メソッドの修飾子」、「throws 例外」
     * メソッドの引数に「..」を記述するどの引数でもマッチする
     */
    @Before("execution(* *..*Controller.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
    	StringBuilder log = new StringBuilder();
    	
    	// クラス名、メソッド名
    	log.append(joinPoint.getTarget().getClass().getName())
    	   .append(", ")
    	   .append(joinPoint.getSignature().getName())
    	   .append("\n");

    	// メソッドに渡される引数を取得
    	Object[] objs = joinPoint.getArgs();
    	
    	for(Object o : objs) {    		
    		Class cls = o.getClass();
    		String clsName = cls.getName();
    		
    		// Formクラスの場合、トレースする
    		if (clsName.endsWith("Form")) {
    			log.append("[" + cls.getName() + "]\n");
    					
    			// フィールドをトレース
    			for (Field field : cls.getDeclaredFields()) {
    				try {
    					field.setAccessible(true);
    					log.append(field.getName() + " = " + field.get(o) + "\n");
    				} catch (IllegalAccessException e) {
    					log.append(field.getName() + " = " + "access denied\n");
    	            }
        		}
    		}
    		
    		// HttpServletRequest
    		if (o instanceof HttpServletRequest) {
    			HttpServletRequest req = (HttpServletRequest)o;
    			Map<String, String[]> reqMap = req.getParameterMap();
    			
    			log.append("[" + o.getClass().getName() + "]\n");
    			
    			// フィールドをトレース
    			for(Map.Entry<String, String[]> e : reqMap.entrySet()) {
    				log.append(e.getKey() + " = " + Arrays.toString(e.getValue()) + "\n");
        		}
    		}
    	}
    	
    	logger.info(log.toString());
    }
}

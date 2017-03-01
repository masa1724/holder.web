package holder.infra.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = 
            LoggerFactory.getLogger(ExceptionHandler.class);
 
    public ModelAndView resolveException(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object object,
                        Exception ex) {

    	logger.error("例外をキャッチしました。", ex);
 
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/system_error");
        
        return mav;
    }
}
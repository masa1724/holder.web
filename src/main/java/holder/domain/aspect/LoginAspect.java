package holder.domain.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

//@Aspect
//@Component
public class LoginAspect {
	@Before("execution(* *..*Service..*(..))")
	public void before(JoinPoint jp) {
		System.out.println("[before] location:" + jp.getSourceLocation() + ", signature:"+ jp.getSignature());
	}
	
	@After("execution(* *..*Service..*(..))")
	public void after(JoinPoint jp) {
		System.out.println("[after] location:" + jp.getSourceLocation() + ", signature:"+ jp.getSignature());
	}
	
	/*
	@AfterReturning(value="execution(* run())", returning="product")
	public void afterReturning(){
		System.out.println("afterReturning");
	}
	}*/
	
	
	/*
	@Around("execution(* run())")
	public String around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("around:" + jp.getArgs() + ",  " + jp.getSignature());
		return (String) jp.proceed();
	}
	*/
	
	/*
	@AfterThrowing(value="execution(* run())", throwing="ex")
	public void afterThrowing() {
		System.out.println("afterThrowing");
	}
	*/
}

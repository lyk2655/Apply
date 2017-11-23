package com.linyk3.apply.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * 切入点：表示在哪个类的哪个方法进行切入。配置有切入点表达式
	 */
	@Pointcut("execution(* com.linyk3.apply.dao.service.impl.*.*(..))")
	public void pointcutExpression() {
		
	}
	
	/**
	 * 前置通知
	 * @param joinPoint
	 */
	@Before("pointcutExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("The method : " + methodName + ",  Args : " + Arrays.asList(joinPoint.getArgs()));
	}
	
	/**
	 * 返回通知
	 * 
	 * 在方法法正常结束受执行的代码
	 * 返回通知是可以访问到方法的返回值的!
	 * 
	 * @param joinPoint
	 * @param returnValue
	 * 
	 */
//	@AfterReturning(value = "pointcutExpression()", returning = "returnValue")
//	public void afterRunningMethod(JoinPoint joinPoint, Object returnValue) {
//		logger.info("The result：" + returnValue);
//	}
	
	/**
	 * 异常通知
	 * 
	 * 在目标方法出现异常时会执行的代码.
	 * 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(value = "pointcutExpression()", throwing = "e")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception e){
		logger.info("The exception ：" + e);
	}
	
	
	/**
	 * 环绕整个方法调用
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
    @Around("pointcutExpression()")    
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {    
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("The result：" + result + ", time cost : " + ( end - begin ) + "ms");
        return result;    
    }    
}

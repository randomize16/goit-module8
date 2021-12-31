package ua.goit.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger LOGGER = LogManager.getLogger();

	@After("@annotation(LogMy)")
	public Object loggMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("Invoke");
		return joinPoint.proceed();
	}

}

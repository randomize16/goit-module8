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

	@Around("@annotation(ua.goit.annotations.LogMe)")
	public Object loggMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		LOGGER.info("Start at " + start);
		Object proceed = joinPoint.proceed();
		long diff = System.currentTimeMillis() - start;
		LOGGER.info("End at " + start + ", execution time " + (diff));
		return proceed;
	}

}

package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Pointcut("execution(public * com.example.demo.service.*.*(..))")
    void loggedServicePointcut() {}

    @Around(value = "loggedServicePointcut()")
    private Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnValue = pjp.proceed();
        stopWatch.stop();
        String loggingMessage = String.format("Execution of %s in %s with result %s took %s ms",
                pjp.getSignature().getName(), pjp.getSignature().getDeclaringType(), returnValue, stopWatch.getTotalTimeMillis());
        logger.info(loggingMessage);
        return returnValue;
    }
}

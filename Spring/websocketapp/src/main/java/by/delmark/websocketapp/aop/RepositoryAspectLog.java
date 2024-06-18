package by.delmark.websocketapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class RepositoryAspectLog {
    @Pointcut("execution(public * by.delmark.websocketapp.repository.*.*(..))")
    public void handleRepositoryPointcut() {}

    @Around("handleRepositoryPointcut()")
    public Object aroundRepositoryExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Repository method {} called with args {}",joinPoint.getSignature().getName(), joinPoint.getArgs());
        Long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        log.info("Repository method {} from {} finished with time execution: {} ms",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getName(),
                System.currentTimeMillis() - startTime
        );
        log.info("Repository method {} from {} returned object: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getName(),
                object
        );
        return object;
    }
}

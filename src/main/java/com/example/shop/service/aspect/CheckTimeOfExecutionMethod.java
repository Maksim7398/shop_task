package com.example.shop.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CheckTimeOfExecutionMethod {

    @Around("@annotation(com.example.shop.service.annotation.CheckTime)")
    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final String nameMethod = joinPoint.getSignature().getName();
        final long time = System.currentTimeMillis() - startTime;
        log.info("method is name: " + nameMethod +  " execution time =  " + time + " ms");
        return proceed;
    }
}

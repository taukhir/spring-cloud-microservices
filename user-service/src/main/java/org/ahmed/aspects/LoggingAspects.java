package org.ahmed.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspects {

    @Before("execution(* org.ahmed.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before method execution: {}", joinPoint.getSignature().getName());
    }

    @After("execution(* org.ahmed.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After method execution: {}", joinPoint.getSignature().getName());
    }
//    @Pointcut("within(org.ahmed.service.*)")
//    public void saveUserMethod() {
//    }
//    @Before("saveUserMethod()")
//    public void logBeforeSaveUser(JoinPoint joinPoint) {
//        log.info("Before saving user: {}", joinPoint.getSignature().getName());
//    }

}
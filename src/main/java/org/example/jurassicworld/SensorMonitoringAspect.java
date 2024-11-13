package org.example.jurassicworld;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SensorMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(SensorMonitoringAspect.class);

    @Before("execution(* org.example.jurassicworld.*.*(..))")
    public void logBeforeProcessing() {
        logger.info("Iniciando procesamiento de datos del sensor...");
    }

    @Around("execution(* org.example.jurassicworld.*.*(..))")
    public Object monitorProcessingTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Procesamiento completado en {} ms.", (endTime - startTime));
        return result;
    }

    @AfterReturning("execution(* org.example.jurassicworld.*.*(..))")
    public void logAfterSuccessfulProcessing() {
        logger.info("Procesamiento de datos del sensor completado exitosamente.");
    }

    @AfterThrowing(pointcut = "execution(* org.example.jurassicworld.*.*(..))", throwing = "error")
    public void logError(Exception error) {
        logger.error("Error durante el procesamiento de datos del sensor: {}", error.getMessage());
    }
}

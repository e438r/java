package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * facade 切面
 *
 * @author shining.cui
 * @since 2017年9月25日17:27:56
 */
@Aspect
@Component
@Order
public class DemoAspect {

    private static final Logger logger = LoggerFactory.getLogger(DemoAspect.class);

    @Pointcut("execution(public * com.example.service.*.*(..))")
    public void facadeAll() {
    }

    @Around("facadeAll()")
    public Object facadeAround(ProceedingJoinPoint jp) {
        String className = jp.getTarget().getClass().getName();
        Object qResponse;
        try {
            qResponse = jp.proceed();
        } catch (Throwable t) {
            qResponse = null;
            logger.info(t.getMessage());
        }
        return qResponse;
    }

}

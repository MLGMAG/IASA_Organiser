package ua.kpi.iasa.IASA_Organiser.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspect {

    private Logger logger;

    @Pointcut("execution(* ua.kpi.iasa.IASA_Organiser.controller.*.*(..))")
    public void controllers() {
    }

    @Pointcut("execution(* ua.kpi.iasa.IASA_Organiser.rest.*.*(..))")
    public void rests() {
    }

    @Pointcut("execution(* ua.kpi.iasa.IASA_Organiser.service.*.*(..))")
    public void services() {
    }

    @Before("controllers() || services() || rests()")
    public void controllerMethodBeginning(JoinPoint joinPoint) {
        logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        if (joinPoint.getArgs().length != 0) {
            String args = Arrays.toString(joinPoint.getArgs());
            logger.debug("Method {}() was called with args {}", joinPoint.getSignature().getName(), args);
            return;
        }
        logger.debug("Method {}() was called...", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "controllers() || services() || rests()", returning = "retVal")
    public void controllerMethodAfter(JoinPoint joinPoint, Object retVal) {
        logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        logger.debug("Method {}() returns=\"{}\"", joinPoint.getSignature().getName(), retVal);
    }

}

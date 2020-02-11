package spittr.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
public class MyAspect {
    @Around("execution(* spittr.web.HomeController.home(..))")
    public void monitorBeforeOperateDB(ProceedingJoinPoint proceedingJoinPoint)
    {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        DaoParamCheck daoParamCheck = method.getAnnotation(DaoParamCheck.class);
        if (daoParamCheck == null) {
            return;
        }
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (daoParamCheck.paramName().equalsIgnoreCase(parameter.getName())) {
            }
        }
        System.out.println("MyAspect out:" + proceedingJoinPoint.getArgs()[0]);

    }
}

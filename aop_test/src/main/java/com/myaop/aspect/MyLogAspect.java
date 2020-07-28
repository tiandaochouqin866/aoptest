package com.myaop.aspect;

import java.lang.reflect.Method;

import com.myaop.annotation.Aspect;
import com.myaop.annotation.Before;
import com.myaop.annotation.Pointcut;

@Aspect
public class MyLogAspect {
    
    @Pointcut("com.aop.biz.*.*")
    public void pointcut() {

    }

    @Before("pointcut")
    public void beforeAdvice(Method method,Object object) {
        System.out.println("-------before------");
        System.out.println(object.getClass().getName());
    }

}
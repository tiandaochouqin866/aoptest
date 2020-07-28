package com.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(** com.aop.biz.*.doSomething(..))")
    public void myPointcut() {

    }
  
    @Before("myPointcut()")
    public void before() {
        System.out.println("before");
    }

    @AfterReturning("myPointcut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @After("myPointcut()")
    public void after() {
        System.out.println("after");
    }
}
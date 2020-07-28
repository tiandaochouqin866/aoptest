package com.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: zhangshuaibiao
 * @Date: 2018/6/13 16:28
 */
@Component
@Aspect
public class OtherAspect {
    @Pointcut("execution(** com.aop.biz.*.doC(..))")
    public void myPointcut() {

    }

    @Before("myPointcut()")
    public void before() {
        System.out.println("before");
    }

    @AfterReturning("myPointcut()")
    public void afterReturning() {
        System.out.println("AfterReturning");
    }

    @After("myPointcut()")
    public void after() {
        System.out.println("after");
    }

    @Before("execution(** com.aop.biz.*.doSomething(..))")
    public void before2() {
        System.out.println("other before2");
    }
}

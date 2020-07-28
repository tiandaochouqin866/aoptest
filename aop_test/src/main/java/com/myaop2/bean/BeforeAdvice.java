package com.myaop2.bean;

import java.lang.reflect.Method;

import com.myaop2.interceptor.MethodInterceptorChain;
import com.myaop2.interceptor.MyMethodInterceptor;

public class BeforeAdvice extends Advice implements MyMethodInterceptor {
    public BeforeAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }

    public void before(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
        ;
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        this.before(target, method, arguments);
        return chain.intercept(method, arguments, target);
    }
}
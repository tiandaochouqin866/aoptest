package com.myaop2.interceptor;

import java.lang.reflect.Method;

import com.myaop2.bean.BeforeAdvice;

public class BeforeMethodInterceptor implements MyMethodInterceptor {

    private BeforeAdvice beforeAdvice;

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        beforeAdvice.before(target, method, arguments);
        return chain.intercept(method, arguments, target);
    }

    public BeforeMethodInterceptor(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    
    
}
package com.myaop2.bean;

import java.lang.reflect.Method;

import com.myaop2.interceptor.MethodInterceptorChain;
import com.myaop2.interceptor.MyMethodInterceptor;

public class AfterAdvice extends Advice implements MyMethodInterceptor {
    
    public AfterAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }

    public void after(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        this.after(target, method, arguments);
        return obj;
    }
}
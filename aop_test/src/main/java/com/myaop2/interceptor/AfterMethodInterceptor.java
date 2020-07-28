package com.myaop2.interceptor;

import java.lang.reflect.Method;

import com.myaop2.bean.AfterAdvice;

public class AfterMethodInterceptor implements MyMethodInterceptor {

    private AfterAdvice afterAdvice;

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        afterAdvice.after(target, method, arguments);
        return obj;
    }

    public AfterMethodInterceptor(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }


    
}
package com.myaop2.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拦截器链
 */
public class MethodInterceptorChain {

    private MyMethodInterceptor[] methodInterceptors;

    public MethodInterceptorChain(MyMethodInterceptor[] methodInterceptors) {
        this.methodInterceptors = methodInterceptors;
    }

    private int index = 0;

    public Object intercept(Method method, Object[] arguments, Object target) {
        if (index == methodInterceptors.length) {
            // call method
            try {
                return method.invoke(target, arguments);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            return methodInterceptors[index++].intercept(method, arguments, target, this);
        }
        return null;
    }
}
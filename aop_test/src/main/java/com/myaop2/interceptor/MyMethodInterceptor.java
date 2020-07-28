package com.myaop2.interceptor;

import java.lang.reflect.Method;

/**
 * 方法拦截器
 * 注意:要和cglib的拦截器区别开
 */
public interface MyMethodInterceptor {

    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain);
    
}
package com.myaop2.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Advice {
    private Method adviceMethod;
    private Aspect aspect;

    public void invokeAspectMethod(final Object target, final Method method, final Object[] args) {
        // 假设advice的参数固定为target、method和args
        try {
            adviceMethod.invoke(aspect, target, method, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Advice(Method adviceMethod, Aspect aspect) {
        this.adviceMethod = adviceMethod;
        this.aspect = aspect;
    }

}
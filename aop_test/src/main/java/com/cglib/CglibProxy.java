package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\workStation\\temp2\\classes");
        MyHandler myHandler = new MyHandler();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BizA.class);
        enhancer.setCallback(myHandler);

        BizA bizA = (BizA) enhancer.create();
        bizA.doSomething();
        // System.out.println(bizA.getClass().getName());
    }
}

class MyHandler implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("----before----");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("----after----");
        return o;
    }
}
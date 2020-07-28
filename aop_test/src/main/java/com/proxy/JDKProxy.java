package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    /**
     * 创建target的代理对象
     * @param target
     * @return
     */
    private static Object createProxy(final Object target) {
        return Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), target.getClass().getInterfaces(),new MyHandler(target));
    }

    public static void main(final String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        final BizAImpl bizAImpl = new BizAImpl();
        final IBizA newBizA = (IBizA) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(),
                bizAImpl.getClass().getInterfaces(),
                new MyHandler(bizAImpl));
        newBizA.doSomething();
    }
    
}

/**
 * 在代理的接口调用时的处理器类
 */
class MyHandler implements InvocationHandler {
    private final Object target;
    public MyHandler(final Object target) {
        this.target = target;
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("-----before----");
        final Object o = method.invoke(this.target, args);
        System.out.println("-----after----");
        return o;
    }
}
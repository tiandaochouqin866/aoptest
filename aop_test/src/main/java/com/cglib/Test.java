package com.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {

    public static void main(String[] args) {
        DemoProxy demoProxy = new DemoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Demo.class);
        enhancer.setCallback(demoProxy);

        Demo demo = (Demo) enhancer.create();
        demo.doSomething();
    }
}
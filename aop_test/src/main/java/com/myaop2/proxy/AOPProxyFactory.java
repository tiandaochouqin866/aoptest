package com.myaop2.proxy;

import java.util.List;

import com.myaop2.bean.Advisor;

public class AOPProxyFactory {

    public Object getProxyObject(List<Advisor> advisors, Object bean) {
        if(isInterface()) {
           return new CglibProxyImpl(advisors,bean).getProxyObject();
        } else {
            return new JdkDynamicProxyImpl(advisors,bean).getProxyObject();
        }
    }

    private boolean isInterface() {
        return false;
    }
    
}
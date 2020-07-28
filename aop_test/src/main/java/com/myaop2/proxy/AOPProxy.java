package com.myaop2.proxy;

import java.util.List;

import com.myaop2.bean.Advisor;

public abstract class AOPProxy {

    //Advisor 集合
    private List<Advisor> advisors;
    //代理目标对象
    private Object target;

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public AOPProxy(List<Advisor> advisors, Object target) {
        this.advisors = advisors;
        this.target = target;
    }

    /**
     * 获取代理对象
     * @return
     */
    protected abstract Object getProxyObject();
    
}
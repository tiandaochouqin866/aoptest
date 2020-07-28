package com.myaop2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myaop2.bean.Advisor;
import com.myaop2.config.ConfigParser;
import com.myaop2.helper.AdvisorHelper;
import com.myaop2.proxy.AOPProxyFactory;

public class AnnotationAOPProxyCreator extends AbstractAOPProxyCreator {

    private ConfigParser configParser;
    private AOPProxyFactory aopProxyFactory;

    
    @Override
    public List<Advisor> buildAdvisors() {
        return configParser.parse();
    }

    @Override
    public Object createProxy(List<Advisor> advisors, Object bean) {
        return aopProxyFactory.getProxyObject(advisors, bean);
    }

    @Override
    public List<Advisor> getMatchedAdvisors() {
        return AdvisorHelper.getMatchedAdvisors(this.getClass(),this.buildAdvisors());
    }

    
}
package com.myaop2;

import java.util.List;

import com.myaop2.bean.Advisor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

public abstract class AbstractAOPProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    //子类可实现
    protected void initBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //构建所有Advisor, 这块可以使用缓存,只需要首次加载时构建即可
    protected abstract List<Advisor> buildAdvisors();

    //获取匹配的Advisor
    protected abstract List<Advisor> getMatchedAdvisors();

    //创建代理对象
    protected abstract Object createProxy(List<Advisor> advisors, Object bean);

    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        this.initBeanFactory(arg0);
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
		return bean;
    }
    

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        //构建所有Advisor
        List<Advisor> advisors = buildAdvisors();
        //获取Advisor
        advisors = this.getMatchedAdvisors();
        //根据获取的Advisor生成代理对象
        Object object = createProxy(advisors,bean);
        //返回代理对象
		return object == null ? bean : object;
    }

}
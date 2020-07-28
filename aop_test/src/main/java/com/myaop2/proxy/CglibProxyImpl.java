package com.myaop2.proxy;

import java.lang.reflect.Method;
import java.util.List;

import com.myaop2.bean.Advisor;
import com.myaop2.helper.AdvisorHelper;
import com.myaop2.interceptor.MethodInterceptorChain;
import com.myaop2.interceptor.MyMethodInterceptor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyImpl extends AOPProxy {

    public CglibProxyImpl(List<Advisor> advisors, Object bean) {
        super(advisors, bean);
    }

    @Override
    protected Object getProxyObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getTarget().getClass());
        enhancer.setCallback(new AOPInterceptor());
        return enhancer.create();
    }

    /**
     * 实现cglib的拦截器,在intercept中将拦截器调用委托给拦截器链MethodInterceptorChain
     */
    private class AOPInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            MyMethodInterceptor[] iterceptors = AdvisorHelper.getMethodInterceptors(CglibProxyImpl.this.getAdvisors(), method);
            Object o = new MethodInterceptorChain(iterceptors).intercept(method, args, obj);
            return o;
        }
    }
    

}
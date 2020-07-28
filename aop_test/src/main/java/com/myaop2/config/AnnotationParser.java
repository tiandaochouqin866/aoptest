package com.myaop2.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myaop2.bean.Advice;
import com.myaop2.bean.Advisor;
import com.myaop2.bean.AfterAdvice;
import com.myaop2.bean.Aspect;
import com.myaop2.bean.BeforeAdvice;
import com.myaop2.bean.Pointcut;

public class AnnotationParser implements ConfigParser {

    private final Map<String, List<Advisor>> cache = new ConcurrentHashMap<>();

    @Override
    public List<Advisor> parse() {
        if(cache != null) {
            return getAdvisorsFromCache();
        } 

        List<Class> allClasses = getAllAspectClasses();
        for (Class class1 : allClasses) {
            cache.putIfAbsent(class1.getName(), getAdvisorsByAspect(class1));
        }

        return getAdvisorsFromCache();
    }

    /**
     * 根据Aspect类生成Advisor类
     * @param class1
     * @return
     */
    private List<Advisor> getAdvisorsByAspect(Class class1) {
        List<Advisor> advisors = new ArrayList<>();
        for (Method method : getAdvisorMethods(class1)) {
            Advisor advisor = null;
            try {
                advisor = getAdvisor(method, class1.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (advisor != null) {
                advisors.add(advisor);
            }
        }
        return advisors;
    }

    private List<Advisor> getAdvisorsFromCache() {
        return new ArrayList<>();
    }

    private List<Class> getAllAspectClasses() {
        
        return new ArrayList<>();
    }

    private List<Method> getAdvisorMethods(Class class1) {
        return null;
    }

    private Advisor getAdvisor(Method method, Object aspect) {
        Advisor advisor = new Advisor();
        advisor.setAspect((Aspect)aspect);
        advisor.setPointcut(getPointcut(method));
        advisor.setAdvice(getAdvice(method, aspect));
        return null;
    }

    private Advice getAdvice(Method method, Object aspect) {
        if(isBefore(method)) {
            return new BeforeAdvice(method, (Aspect) aspect);
        }

        if(isAfter(method)) {
            return new AfterAdvice(method, (Aspect) aspect);
        }

        return null;
    }

    /**
     * 根据方法找到Annotation,进一步定位到Advice的类型
     * @param method
     * @return
     */
    private boolean isBefore(Method method) {
        return false;
    }

    private boolean isAfter(Method method) {
        return false;
    }

    private Pointcut getPointcut(Method method) {
        return null;
    }
    
}
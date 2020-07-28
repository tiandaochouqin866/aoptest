package com.myaop2.helper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.myaop2.bean.Advisor;
import com.myaop2.bean.AfterAdvice;
import com.myaop2.bean.BeforeAdvice;
import com.myaop2.interceptor.MyMethodInterceptor;

import org.aspectj.weaver.tools.ShadowMatch;

public class AdvisorHelper {

    public static MyMethodInterceptor[] getMethodInterceptors(List<Advisor> advisors, Method method) {
        List<MyMethodInterceptor> list = new ArrayList<MyMethodInterceptor>();
        for (Advisor advisor : advisors) {
            if(isMatch(advisor, method)) {
                if(advisor.getAdvice() instanceof BeforeAdvice) {
                    list.add((BeforeAdvice) advisor.getAdvice());
                }

                if(advisor.getAdvice() instanceof AfterAdvice) {
                    list.add((AfterAdvice) advisor.getAdvice());
                }
            }
        }
        return (MyMethodInterceptor[]) list.toArray();
    }

    public static boolean isMatch(Advisor advisor, Method method) {
        return advisor.getPointcut().buildPointcutExpression().matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 从所有的Advisor中获取匹配的
     * @param advisors
     * @return
     */
    public static List<Advisor> getMatchedAdvisors(Class cls, List<Advisor> advisors) {
        List<Advisor> aList = new ArrayList<>();
        for (Method method : cls.getDeclaredMethods()) {
            for (Advisor advisor : advisors) {
                ShadowMatch match = advisor.getPointcut()
                                    .buildPointcutExpression()
                                    .matchesMethodExecution(method);
                if(match.alwaysMatches()) {
                    aList.add(advisor);
                }
            }
        }
        return aList;
    }
    
}
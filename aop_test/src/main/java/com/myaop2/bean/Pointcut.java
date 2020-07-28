package com.myaop2.bean;

import org.aspectj.weaver.tools.PointcutExpression;

public interface Pointcut {
    
    public String getExpression();

    /**
     * 转换为AspectJ的切入点表达式
     * @return
     */
    public PointcutExpression buildPointcutExpression();

}
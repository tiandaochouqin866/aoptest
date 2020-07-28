package com.myaop2.bean;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;

public class AspectJPointcut implements Pointcut {

    public String expression;

    public AspectJPointcut(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }

    @Override
    public PointcutExpression buildPointcutExpression() {
        PointcutParser parser = PointcutParser
        .getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
        return parser.parsePointcutExpression(this.expression);
    }
    
}
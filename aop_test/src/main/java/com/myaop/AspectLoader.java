package com.myaop;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aop.biz.BizA;
import com.aop.biz.BizB;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.myaop.annotation.Aspect;
import com.myaop.annotation.Before;
import com.myaop.annotation.Pointcut;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AspectLoader {

    // 扫描包
    private static final String scan_package = "com.myaop.aspect";
    /**
     * 相当于Spring bean容器,以Class的simpleName作为key,保存实例化之后的对象
     *  */ 
    Map<String, Object> beanContainer = new HashMap<>();

    /**
     * 初始化
     * @throws IOException
     */
    public void init() throws Exception {
        final ClassPath cp = ClassPath.from(AspectLoader.class.getClassLoader());
        final ImmutableSet<ClassPath.ClassInfo> allClasses = cp.getAllClasses();

        //1.获取具有@Aspect注解的类
        Class aspectClass = getAspectClass(allClasses.asList());

        //2-1.获取before
        Before before = getBefore(aspectClass);

        //2-2.获取before相关的方法
        Method beforeMethod = getBeforeMethod(aspectClass);

        //2-3.获取切点
        String methodName = before.value().replace("()", "");
        Pointcut pointcut = getPointcut(aspectClass, methodName);

        //3.构建advice和pointcut关系
        //...

        //4.定位切点目标类
        List<Class> targets = getTargetClasses(allClasses.asList(),pointcut.value());

        //5-1.生成aspect实例
        Object aspectObject = aspectClass.newInstance();

        //5-2.通过动态代理，将advice添加到代理类的interceptor中，并最终把代理对象放入容器中以供调用
        for (Class c : targets) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(c);
            enhancer.setCallback(new MethodInterceptor(){
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    // TODO Auto-generated method stub
                    beforeMethod.invoke(aspectObject,method,obj);
                    proxy.invokeSuper(obj, args);
                    return obj;
                }
            });

            Object o = enhancer.create();
            this.beanContainer.put(c.getSimpleName(),o);
        }

    }

    private List<Class> getTargetClasses(List<ClassInfo> allClasses, String pointcut)
            throws ClassNotFoundException {
        List<Class> list = new ArrayList<>();
        //找到切点下的所有类
        for (final ClassInfo cli : allClasses) {
            if(cli.getName().startsWith(pointcut.substring(0, pointcut.indexOf("*")-1))) {
                list.add(Class.forName(cli.getName()));
            }
        }
        return list;
    }

    
    private Class getAspectClass(List<ClassInfo> list) throws ClassNotFoundException {
        for (final ClassInfo classInfo : list) {
            if(classInfo.getPackageName().equals(scan_package)) {
                Class cls = Class.forName(classInfo.getName());
                Annotation a = cls.getAnnotation(Aspect.class);
                if(a != null) {
                    return cls;
                }
            }
        }
        return null;
    }


    private Pointcut getPointcut(Class cls, String methodName) throws ClassNotFoundException {
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            Pointcut[] pointcuts = m.getAnnotationsByType(Pointcut.class);
            if(pointcuts.length > 0 && m.getName().equals(methodName)) {
                // pointcut = pointcuts[0];
                return pointcuts[0];
            }
        }
        return null;
    }


    private Before getBefore(Class cls) throws ClassNotFoundException {
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            Before[] befores = m.getAnnotationsByType(Before.class);
            if(befores.length > 0) {
                return befores[0];
            }
        }
        return null;
    }

    private Method getBeforeMethod(Class cls) throws ClassNotFoundException {
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            Before[] befores = m.getAnnotationsByType(Before.class);
            if(befores.length > 0) {
                return m;
            }
        }
        return null;
    }

    public static void main(final String[] args) throws Exception {
        AspectLoader aspectLoader = new AspectLoader();
        aspectLoader.init();
        BizA bizA = (BizA) aspectLoader.beanContainer.get(BizA.class.getSimpleName());
        bizA.doSomething();

        BizB bizB = (BizB) aspectLoader.beanContainer.get(BizB.class.getSimpleName());
        bizB.doSomething();
    }

}
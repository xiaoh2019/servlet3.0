package com.cyzs.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author xiaoH
 * @create 2019-07-01-11:58
 */

/**
 *容器启动会把value里面类型的子类和子接口传递到Set<Class<?>> c  set集合中
 */
@HandlesTypes(value = {HelloServlet.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     *
     * @param c     @HandlesTypes指定的类所有子代类型，（实现类，子接口，抽象类）
     * @param ctx   上下文对象
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        /**
         * c传入类的Class集合，可以通过反射创建对象
         */
        for (Class<?> aClass : c) {
            System.out.println(aClass);
        }

        //注册三大组件
        //注册servlet
        ServletRegistration.Dynamic servlet = ctx.addServlet("userServlet", UserServlet.class);
        servlet.addMapping("/user");

        //注册filter
        FilterRegistration.Dynamic myFilter = ctx.addFilter("myFilter", MyFilter.class);
        myFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

        //注册监听器
        ctx.addListener(MyListener.class);

    }
}

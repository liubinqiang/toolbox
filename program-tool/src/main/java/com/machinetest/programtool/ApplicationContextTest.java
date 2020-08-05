package com.machinetest.programtool;

import com.machinetest.programtool.service.IJavaService;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liubinqiang
 */
@EnableAspectJAutoProxy
@ComponentScan("com.machinetest.programtool.*")
public class ApplicationContextTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextTest.class);
        IJavaService javaService = annotationConfigApplicationContext.getBean(IJavaService.class);
        System.out.println(javaService.getClass().getName());

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    }
}

package com.machinetest.programtool;

import com.machinetest.programtool.service.IDbService;
import com.machinetest.programtool.service.IJavaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramToolApplicationTests {

    /*    @Autowired
        AnnotationConfigApplicationContext annotationConfigApplicationContext;*/
    @Autowired
    private IDbService dbService;

    @Test
    public void contextLoads() {

/*        IJavaService javaService = annotationConfigApplicationContext.getBean(IJavaService.class);
        System.out.println(javaService.getClass().getName());*/
    }

    @Test
    public void sync() {
        dbService.syncDefaultValue();
    }



}

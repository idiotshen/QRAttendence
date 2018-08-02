package com.shen.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils {

    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}

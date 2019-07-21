package com.milaev.medicine;

import com.milaev.medicine.test.Bean;
import com.milaev.medicine.test.BeanAnnotation;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Go {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Bean bean01 = context.getBean("ffBean", Bean.class);
//        System.out.println(bean01.getName());
//        context.close();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotation.xml");
        BeanAnnotation bean02 = context.getBean("ffBeanByAnn", BeanAnnotation.class);
        System.out.println(bean02.getName());
        context.close();
    }
}

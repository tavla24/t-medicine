package com.milaev.medicine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Go {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Bean bean01 = context.getBean("ffBean", Bean.class);
//        System.out.println(bean01.getName());
//        context.close();

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotation.xml");
//        BeanAnnotation bean02 = context.getBean("ffBeanByAnn", BeanAnnotation.class);
//        System.out.println(bean02.getName());
//        context.close();
        
        connectionTest();
    }

    public static void connectionTest() {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "5220";
        System.out.println("Connecting...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}

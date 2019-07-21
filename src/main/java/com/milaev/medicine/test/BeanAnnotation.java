package com.milaev.medicine.test;

import org.springframework.stereotype.Component;

@Component("ffBeanByAnn")
public class BeanAnnotation {
    private String name = "nuuul";

//    public BeanAnnotation(String name){
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

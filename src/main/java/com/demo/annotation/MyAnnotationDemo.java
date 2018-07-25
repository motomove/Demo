package com.demo.annotation;

import java.lang.reflect.Field;

public class MyAnnotationDemo {

    public static void main(String[] args) {
        Apple apple = new Apple();
        System.out.println("apple = " + apple.getName());

        getFruitInfo(apple.getClass());
    }

    public static void getFruitInfo(Class<?> clazz){
        Field[] field = clazz.getDeclaredFields();

        for(Field f : field){
            boolean isAnnotation = f.isAnnotationPresent(FruitName.class);

            System.out.println("isAnnotation = " + isAnnotation);

            FruitName fruitName = f.getAnnotation(FruitName.class);
            String val = fruitName.value();
            System.out.println("val = " + val);
        }
    }
}

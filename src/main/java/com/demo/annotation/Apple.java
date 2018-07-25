package com.demo.annotation;

public class Apple {

    @FruitName("Big Apple")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

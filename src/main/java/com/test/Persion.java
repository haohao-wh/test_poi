package com.test;

public interface Persion {
    void run();
    void eat();
    void drink();
    default String rap(){
        System.out.println("this is rap method!!");
        return "rap";
    }
    default String rap1(){
        System.out.println("this is rap1 method!!");
        return "rap";
    }
}

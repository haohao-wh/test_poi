package com.test;

/**
 * @ClassNmae Man
 * @Discription
 * @Author 王浩
 * @Date 2020/4/14  11:17
 * @Version 1.0
 */
public abstract class Man implements Persion{

    public  abstract  void talk();

    @Override
    public void eat() {

    }

    @Override
    public void drink() {

    }
    void song(){
        System.out.println("song");
    };
}

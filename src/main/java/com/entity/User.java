package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNmae User
 * @Discription
 * @Author 王浩
 * @Date 2020/4/9  12:30
 * @Version 1.0
 */
public class User implements Serializable {
    private String id;
    private String name;
    private int age;
    private Date date;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User() {
    }

    public User(String id, String name, int age, Date date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
    }
}

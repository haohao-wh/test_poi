package com.config;
import com.entity.User;

/**
 * @ClassNmae myEnumClass
 * @Discription
 * @Author 王浩
 * @Date 2020/4/10  15:10
 * @Version 1.0
 */
public  enum  MyEnumSingleton{

    USER;
    private User user;

    public User getUser() {
        return new User();
    }

    }

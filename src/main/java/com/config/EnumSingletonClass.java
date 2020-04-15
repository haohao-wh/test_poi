package com.config;

/**
 * @ClassNmae EnumSingletonClass
 * @Discription
 * @Author 王浩
 * @Date 2020/4/10  15:42
 * @Version 1.0
 */
public class EnumSingletonClass {
    private EnumSingletonClass() {
    }
    private enum SingletonEnum{
        User;
        private final EnumSingletonClass instance;
        SingletonEnum(){
            instance = new EnumSingletonClass();
        }

        public EnumSingletonClass getInstance() {
            return instance;
        }
    }

    public static EnumSingletonClass getInstance(){
        return SingletonEnum.User.getInstance();
    }
}

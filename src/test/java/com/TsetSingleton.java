package com;

import com.config.EnumSingletonClass;
import com.config.MyEnumSingleton;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassNmae TsetSingleton
 * @Discription
 * @Author 王浩
 * @Date 2020/4/10  15:29
 * @Version 1.0
 */
public class TsetSingleton {
    static CountDownLatch cl =  new CountDownLatch(100);
    static EnumSingletonClass instance;
    @Test
    public void test() throws InterruptedException {

        // ;
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    instance = EnumSingletonClass.getInstance();
                    System.out.println("this is run method");
                }
            });
            thread.start();
            Thread.sleep(500);
            cl.countDown();
            System.out.println(instance);
        }
    }
    @Test
    public void test2(){
        String str = "12231";
        int i = str.hashCode();
        System.out.println(i);
        int i1 = Integer.hashCode(1);
        int i2 = Float.hashCode((float) 1.2);
    }
}

package com.test;

/**
 * @ClassNmae TestMan
 * @Discription
 * @Author 王浩
 * @Date 2020/4/14  16:02
 * @Version 1.0
 */
public class TestMan {
    public static void main(String[] args) throws InterruptedException {
       /* String rap = new MyMan().rap();
        System.out.println(rap);*/
        UserDao run = (x) -> {
            System.out.println("begin  "+x);
            Thread.sleep(5000);
            System.out.println("end");
            return x;
        };
        String xiao_hua = run.run("xiao hua");
        System.out.println(xiao_hua);
    }

}

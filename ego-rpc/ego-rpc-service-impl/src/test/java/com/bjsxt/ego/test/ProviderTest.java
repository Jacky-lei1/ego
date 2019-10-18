package com.bjsxt.ego.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author lvyelanshan
 * @create 2019-10-18 9:27
 */
public class ProviderTest {
    public static void main(String[] args) {
        /**
         * 加载Spring容器，完成服务的发布
         */
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-tx.xml", "spring/applicationContext-service.xml", "spring/applicationContext-dubbo.xml", "spring/applicationContext-dao.xml");

        //启动Spring容器
        ac.start();

        //阻塞程序的运行,保证主线程不死，等待消费者的调用
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ac.stop();
    }
}

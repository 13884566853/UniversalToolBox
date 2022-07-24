package com.wwt.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
/**
 * @author wwt
 * @title: Application
 * @description: 使用方法，用这个Application替代项目的启动类，就可以运行验证了
 * @date 2022/6/25 13:43
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(Application.class,args);
    }

}

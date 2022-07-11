package com.wwt.example;

import com.wwt.example.jvm.dynamicuploadjar.Calculator;
import com.wwt.example.jvm.dynamicuploadjar.CalculatorImpl;
import com.wwt.example.jvm.dynamicuploadjar.DeployUtils;
import com.wwt.example.jvm.dynamicuploadjar.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;

import static com.wwt.example.jvm.dynamicuploadjar.HotDeploy.hotDeployWithReflect;

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

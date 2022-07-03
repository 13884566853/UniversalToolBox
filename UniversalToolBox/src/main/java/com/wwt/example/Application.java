package com.wwt.example;

import com.wwt.example.jvm.dynamicuploadjar.DeployUtils;
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
 * @description: TODO
 * @date 2022/6/25 13:43
 */
@SpringBootApplication
public class Application {
    /**
     * 用户把jar包上传到系统的指定目录下，这里定义上传jar文件路径为jarAddress，jar的Url路径为jarPath。
     **/
    private static String jarAddress = "E:\\github_ws\\UniversalToolBox\\target\\UniversalToolBox-1.0-SNAPSHOT.jar";
    private static String jarPath = "file:\\" + jarAddress;

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(Application.class,args);
        // JarFile jarFile = new JarFile(jarAddress);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(jarPath)}, Thread.currentThread().getContextClassLoader());

        // ApplicationContext 理解为spring容器的上下文,通过上下文操作容器中bean.
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //while (true) {
            try {
                //hotDeployWithReflect(jarAddress,urlClassLoader);
            hotDeployWithSpring(defaultListableBeanFactory);
            TimeUnit.MINUTES.sleep(1);
            delete(defaultListableBeanFactory);
            } catch (Exception e) {
                e.printStackTrace();


            }
        //}



    }

    /**
     * 如果用户上传的jar包含了spring的上下文，那么就需要扫描jar包里的所有需要注入spring容器的bean，
     * 注册到当前系统的spring容器中。其实，这就是一个类的热加载+动态注册的过程。
     * 加入jar包后 动态注册bean到spring容器，包括bean的依赖
     *
     * 在这个过程中，将jar加载到当前线程类加载器的过程和之前反射方式是一样的。
     * 然后扫描jar包下所有的类文件，获取到完整类名，并使用当前线程类加载器加载出该类名对应的class对象。
     * 判断该class对象是否带有spring的注解，如果包含，则将该对象注册到系统的spring容器中。
     */
    public static void hotDeployWithSpring(DefaultListableBeanFactory defaultListableBeanFactory) throws Exception {
        Set<String> classNameSet = DeployUtils.readJarFile(jarAddress);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(jarPath)}, Thread.currentThread().getContextClassLoader());
        for (String className : classNameSet) {
            Class clazz = urlClassLoader.loadClass(className);
            if (DeployUtils.isSpringBeanClass(clazz)) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                defaultListableBeanFactory.registerBeanDefinition(DeployUtils.transformName(className), beanDefinitionBuilder.getBeanDefinition());
                System.out.println("动态注册bean到spring容器"+DeployUtils.transformName(className));
            }
        }
    }

    /**
     * 删除jar包时 需要在spring容器删除注入
     */
    public static void delete(DefaultListableBeanFactory defaultListableBeanFactory) throws Exception {
        Set<String> classNameSet = DeployUtils.readJarFile(jarAddress);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(jarPath)}, Thread.currentThread().getContextClassLoader());
        for (String className : classNameSet) {
            Class clazz = urlClassLoader.loadClass(className);
            if (DeployUtils.isSpringBeanClass(clazz)) {
                defaultListableBeanFactory.removeBeanDefinition(DeployUtils.transformName(className));
                System.out.println("卸載" + DeployUtils.transformName(className));
            }
        }
    }



}

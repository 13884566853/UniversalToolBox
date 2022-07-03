package com.wwt.example.jvm.dynamicuploadjar;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author wwt
 * @title: DeployUtils
 * @description: TODO
 * @date 2022/7/3 20:53
 * 包含读取jar包所有类文件的方法、判断class对象是否包含sping注解的方法、获取注册对象对象名的方法
 */
public class DeployUtils {
    /**
     * 读取jar包中所有类文件
     */
    public static Set<String> readJarFile(String jarAddress) throws IOException {
        Set<String> classNameSet = new HashSet<>();
        JarFile jarFile = new JarFile(jarAddress);
        //遍历整个jar文件
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class")) {
                String className = name.replace(".class", "").replaceAll("/", ".");
                classNameSet.add(className);
            }
        }
        return classNameSet;
    }
    /**
     * 方法描述 判断class对象是否带有spring的注解
     */
    public static boolean isSpringBeanClass(Class<?> cla) {
        if (cla == null) {
            return false;
        }
        //是否是接口
        if (cla.isInterface()) {
            return false;
        }
        //是否是抽象类
        if (Modifier.isAbstract(cla.getModifiers())) {
            return false;
        }
        if (cla.getAnnotation(Component.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Repository.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Service.class) != null) {
            return true;
        }
        return false;
    }
    /**
     * 类名首字母小写 作为spring容器beanMap的key
     */
    public static String transformName(String className) {
        String tmpstr = className.substring(className.lastIndexOf(".") + 1);
        return tmpstr.substring(0, 1).toLowerCase() + tmpstr.substring(1);
    }


}

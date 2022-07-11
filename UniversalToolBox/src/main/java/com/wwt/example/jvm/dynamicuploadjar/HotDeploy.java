package com.wwt.example.jvm.dynamicuploadjar;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wwt
 * @title: HotDeploy
 * @description: TODO
 * @date 2022/7/3 20:46
 */
public class HotDeploy {


    /**
     * 并且可以要求用户填写jar包中接口实现类的完整类名。
     * 接下来系统要把上传的jar包加载到当前线程的类加载器中，然后通过完整类名，
     * 加载得到该实现的Class对象。然后反射调用即可，完整代码：
     *
     * 热加载Calculator接口的实现 反射方式
     **/
    public static void hotDeployWithReflect(String jarPath, URLClassLoader urlClassLoader) throws Exception {
        Class clazz = urlClassLoader.loadClass("com.wwt.example.jvm.dynamicuploadjar.CalculatorImpl");
        Calculator calculator = (Calculator) clazz.newInstance();
        int result = calculator.add(1, 2);
        System.out.println(result);
    }



}

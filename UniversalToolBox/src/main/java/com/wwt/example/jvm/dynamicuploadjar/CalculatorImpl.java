package com.wwt.example.jvm.dynamicuploadjar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wwt
 * @title: CalculatorImpl
 * @description: TODO
 * @date 2022/7/3 20:41
 * 2.接口的简单实现
 * 考虑到用户实现接口的两种方式，使用spring上下文管理的方式，或者不依赖spring管理的方式，
 * 这里称它们为注解方式和反射方式。calculate方法对应注解方式，add方法对应反射方式。
 * 计算器接口实现类的代码如下
 */
@Service
public class CalculatorImpl implements Calculator {
    @Autowired
    CalculatorCore calculatorCore;
    /**
     * 注解方式
     */
    @Override
    public int calculate(int a, int b) {
        int c = calculatorCore.add(a, b);
        return c;
    }
    /**
     * 反射方式
     */
    @Override
    public int add(int a, int b) {
        return new CalculatorCore().add(a, b);
    }
}

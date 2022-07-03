package com.wwt.example.jvm.dynamicuploadjar;

import org.springframework.stereotype.Service;

/**
 * @author wwt
 * @title: CalculatorCore
 * @description: 3.这里注入CalculatorCore的目的是为了验证在注解模式下，
 * 系统可以完整的构造出bean的依赖体系，并注册到当前spring容器中。
 * CalculatorCore的代码如下：
 */
@Service
public class CalculatorCore {
    public int add(int a, int b) {
        return a+b;
    }
}
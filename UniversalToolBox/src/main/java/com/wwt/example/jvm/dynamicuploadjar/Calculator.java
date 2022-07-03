package com.wwt.example.jvm.dynamicuploadjar;

/**
 * @author wwt
 * @title: Calculator
 * @description: TODO
 * @date 2022/7/3 20:40
 * 近期开发系统过程中遇到的一个需求，系统给定一个接口，
 * 用户可以自定义开发该接口的实现，并将实现打成jar包，
 * 上传到系统中。系统完成热部署，并切换该接口的实现
 *
 * 1.定义简单接口
 */
public interface Calculator {
    int calculate(int a, int b);
    int add(int a, int b);
}

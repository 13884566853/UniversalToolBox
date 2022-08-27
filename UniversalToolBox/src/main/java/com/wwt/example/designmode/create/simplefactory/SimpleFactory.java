package com.wwt.example.designmode.create.simplefactory;

/**
 * @author wwt
 * @title: SimpleFactory
 * @description: 缺陷不太符合开闭原则
 * @date 2022/8/27 15:49
 */
public class SimpleFactory {
    public static void main(String[] args) {
        // Apple apple = new Apple();
        //直接问工厂要，而不是我们自己去创建
        Fruit fruit = FruitFactory.getFruit("橘子");
        System.out.println(fruit);
    }
}

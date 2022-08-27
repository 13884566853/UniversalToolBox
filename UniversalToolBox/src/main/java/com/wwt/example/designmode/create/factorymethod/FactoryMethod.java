package com.wwt.example.designmode.create.factorymethod;

import com.wwt.example.designmode.create.simplefactory.Fruit;

import java.util.function.Supplier;

/**
 * @author wwt
 * @title: FactoryMethod
 * @description: 比如我们现在要吃一个苹果，那么就直接通过苹果工厂来获取苹果
 * @date 2022/8/27 17:11
 */
public class FactoryMethod {
    public static void main(String[] args) {
        eat(new AppleFactory()::getFruit);
    }

    //此方法模拟吃掉一个水果
    private static void eat(Supplier<Fruit> supplier){
        System.out.println(supplier.get()+" 被吃掉了，真好吃。");
    }
}
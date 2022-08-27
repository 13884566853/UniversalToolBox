package com.wwt.example.designmode.create.factorymethod;

import com.wwt.example.designmode.create.simplefactory.Fruit;

/**
 * @author wwt
 * @title: FruitFactory
 * @description: 将水果工厂抽象为抽象类，添加泛型T由子类指定水果类型
 * @date 2022/8/27 15:53
 */
public abstract class FruitFactory<T extends Fruit> {
    //不同的水果工厂，通过此方法生产不同的水果
    public abstract T getFruit();
}
package com.wwt.example.designmode.create.factorymethod;

import com.wwt.example.designmode.create.simplefactory.Apple;

/**
 * @author wwt
 * @title: AppleFactory
 * @description: 苹果工厂，直接返回Apple，一步到位
 * @date 2022/8/27 15:54
 */
public class AppleFactory extends FruitFactory<Apple> {
    @Override
    public Apple getFruit() {
        return new Apple();
    }
}
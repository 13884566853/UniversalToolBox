package com.wwt.example.designmode.create.factorymethod;

import com.wwt.example.designmode.create.simplefactory.Apple;
import com.wwt.example.designmode.create.simplefactory.Orange;
import org.springframework.boot.origin.Origin;

/**
 * @author wwt
 * @title: OriginFactory
 * @description: TODO
 * @date 2022/8/27 15:54
 */
public class OrangeFactory extends FruitFactory<Orange> {
    @Override
    public Orange getFruit() {
        return new Orange();
    }
}
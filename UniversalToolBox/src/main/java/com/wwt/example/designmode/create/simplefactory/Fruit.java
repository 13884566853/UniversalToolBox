package com.wwt.example.designmode.create.simplefactory;

/**
 * @author wwt
 * @title: Fruit
 * @description: //水果抽象类
 * @date 2022/8/27 15:47
 */
public abstract class Fruit {
    private final String name;

    public Fruit(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        //打印一下当前水果名称，还有对象的hashCode
        return name+"@"+hashCode();
    }
}
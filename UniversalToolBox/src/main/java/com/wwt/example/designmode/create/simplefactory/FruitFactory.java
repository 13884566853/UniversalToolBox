package com.wwt.example.designmode.create.simplefactory;

/**
 * @author wwt
 * @title: FruitFactory
 * @description: TODO
 * @date 2022/8/27 15:49
 */
public class FruitFactory {
    /**
     * 这里就直接来一个静态方法根据指定类型进行创建
     * @param type 水果类型
     * @return 对应的水果对象
     */
    public static Fruit getFruit(String type) {
        switch (type) {
            case "苹果":
                return new Apple();
            case "橘子":
                return new Orange();
            default:
                return null;
        }
    }
}
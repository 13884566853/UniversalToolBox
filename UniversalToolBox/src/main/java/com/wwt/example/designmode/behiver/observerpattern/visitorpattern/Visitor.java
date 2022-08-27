package com.wwt.example.designmode.behiver.observerpattern.visitorpattern;

/**
 * @author wwt
 * @title: Vistor
 * @description: TODO
 * @date 2022/8/27 15:29
 * 定义一个访问者接口
 */
public interface Visitor {
    //visit方法来访问我们的奖项
    void visit(Prize prize);
}

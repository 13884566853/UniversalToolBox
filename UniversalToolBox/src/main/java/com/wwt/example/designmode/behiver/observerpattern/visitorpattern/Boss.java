package com.wwt.example.designmode.behiver.observerpattern.visitorpattern;

/**
 * @author wwt
 * @title: Boss
 * @description: 访问者相关的实现
 * @date 2022/8/27 15:31
 *
 */
public class Boss implements Visitor{    //你的公司老板作为一个访问者
    @Override
    public void visit(Prize prize) {   //你的老板只关心这些能不能为公司带来什么效益，奖本身并不重要
        System.out.println("你的奖项大么，能够为公司带来什么效益么？");
        System.out.println("还不如老老实实加班给我多干干，别去搞这些没用的");
    }
}

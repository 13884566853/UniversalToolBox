package com.wwt.example.designmode.behiver.observerpattern.visitorpattern;

/**
 * @author wwt
 * @title: Teacher
 * @description: 访问者相关的实现
 * @date 2022/8/27 15:30
 *
 */
// 指导老师作为一个访问者
public class Teacher implements Visitor {
    // 他只关心你得了什么奖以及是几等奖，这也关乎老师的荣誉
    @Override
    public void visit(Prize prize) {
        System.out.println("你得奖是什么奖？"+prize.name);
        System.out.println("你得了几等奖？"+prize.level);
    }
}
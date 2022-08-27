package com.wwt.example.designmode.behiver.observerpattern.visitorpattern;

/**
 * @author wwt
 * @title: Prize
 * @description: TODO
 * @date 2022/8/27 15:29
 */
public class Prize {   //奖
    String name;   //比赛名称
    String level;    //等级

    public Prize(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}

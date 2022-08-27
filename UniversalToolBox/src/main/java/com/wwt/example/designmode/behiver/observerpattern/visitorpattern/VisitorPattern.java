package com.wwt.example.designmode.behiver.observerpattern.visitorpattern;

/**
 * @author wwt
 * @title: VisitorPattern
 * @description: ，但是不同的访问者对于某一件事务的处理可能会不同。
 * 访问者模式把数据结构和作用于结构上的操作解耦，使得操作集合可相对自由地演化，
 * 我们上面就是将奖项本身的属性和对于奖项的不同操作进行了分离。
 * @date 2022/8/27 15:32
 */
public class VisitorPattern {
    public static void main(String[] args) {
        Prize prize = new Prize("ACM","区域金奖");
        Teacher teacher = new Teacher();
        Boss boss = new Boss();
        teacher.visit(prize);
        boss.visit(prize);
    }
}

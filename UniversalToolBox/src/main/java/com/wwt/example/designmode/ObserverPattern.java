package com.wwt.example.designmode;

import java.util.Date;
import java.util.Observable;

/**
 * @author wwt
 * @title: ObserverPattern
 * @description: TODO
 * @date 2022/8/27 14:53
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver((o, arg) -> System.out.println("监听到变化，并得到参数："+arg));
        //注意这里的Observer是java.util包下提供的
        //进行修改操作
        subject.modify();
        //进行修改操作
        subject.modifyWithArg("test");
    }
}
class Subject extends Observable {   //继承此抽象类表示支持观察者

    public void modify(){
        System.out.println("对对象进行修改！");
        //当对对象修改后，需要setChanged来设定为已修改状态
        this.setChanged();
        //使用notifyObservers方法来通知所有的观察者
        this.notifyObservers(new Date());
        //注意只有已修改状态下通知观察者才会有效，并且可以给观察者传递参数，这里传递了一个时间对象
    }
    public void modifyWithArg(String str){
        System.out.println("对对象进行修改！带参数");
        //当对对象修改后，需要setChanged来设定为已修改状态
        this.setChanged();
        //使用notifyObservers方法来通知所有的观察者
        this.notifyObservers(str);
        //注意只有已修改状态下通知观察者才会有效，并且可以给观察者传递参数，这里传递了一个时间对象
    }
}
package com.wwt.example.annotation;

import java.lang.annotation.*;

/**
 * @author wwt
 * @title: BankAPI
 * @description: 接口 URL 地址和接口说明
 * @date 2022/7/19 0:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";
    String url() default "";
}
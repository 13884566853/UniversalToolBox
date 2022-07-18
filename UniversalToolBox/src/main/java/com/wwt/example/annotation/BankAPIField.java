package com.wwt.example.annotation;

import java.lang.annotation.*;

/**
 * @author wwt
 * @title: BankAPIField
 * @description: 用于描述接口的每一个字段规范，包含参数的次序、类型和长度三个属性：
 * @date 2022/7/19 0:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order() default -1;
    int length() default -1;
    String type() default "";
}

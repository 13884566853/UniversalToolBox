package com.wwt.example.annotation;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author wwt
 * @title: BankService
 * @description: 一、需求
 *
 * @date 2022/7/19 0:11
 * 假设银行提供了一些 API 接口，对参数的序列化有点特殊，不使用 JSON，而是需要我们把参数依次拼在一起构成一个大字符串。
 *
 * 按照银行提供的 API 文档的顺序，把所有参数构成定长的数据，然后拼接在一起作为整个字符串。
 * 因为每一种参数都有固定长度，未达到长度时需要做填充处理：
 * 字符串类型的参数不满长度部分需要以下划线右填充，也就是字符串内容靠左；
 * 数字类型的参数不满长度部分以 0 左填充，也就是实际数字靠右；
 * 货币类型的表示需要把金额向下舍入 2 位到分，以分为单位，作为数字类型同样进行左填充。
 * 对所有参数做 MD5 操作作为签名（为了方便理解，Demo 中不涉及加盐处理）。
 * 比如，创建用户方法和支付方法的定义是这样的：
 *
 * 可以看到，这段代码的重复粒度更细：
 *
 * 三种标准数据类型的处理逻辑有重复，稍有不慎就会出现 Bug；
 * 处理流程中字符串拼接、加签和发请求的逻辑，在所有方法重复；
 * 实际方法的入参的参数类型和顺序，不一定和接口要求一致，容易出错；
 * 代码层面针对每一个参数硬编码，无法清晰地进行核对，如果参数达到几十个、上百个，出错的概率极大。
 * 那应该如何改造这段代码呢？没错，就是要用注解和反射！NewBankService为改造后结果
 */
public class BankService {

    //创建用户方法
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-10s", name).replace(' ', '_'));
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-18s", identity).replace(' ', '_'));
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%05d", age));
        //字符串靠左，多余的地方用_填充
        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_'));
        //最后加上MD5作为签名
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        return Request.Post("http://localhost:45678/reflection/bank/createUser")
                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
    }

    //支付方法
    public static String pay(long userId, BigDecimal amount) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%020d", userId));
        //金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
        //最后加上MD5作为签名
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        return Request.Post("http://localhost:45678/reflection/bank/pay")
                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
    }
}
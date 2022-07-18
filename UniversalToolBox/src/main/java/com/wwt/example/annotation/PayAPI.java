package com.wwt.example.annotation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wwt
 * @title: PayAPI
 * @description: TODO
 * @date 2022/7/19 0:36
 */
@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "N", length = 20)
    private long userId;
    @BankAPIField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}
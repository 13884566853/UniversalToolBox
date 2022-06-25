package com.wwt.example.indepartment.poi;

import lombok.Data;

/**
 * @author wwt
 * @title: OrderDTO
 * @description: TODO
 * @date 2022/6/25 14:22
 */
@Data
public class OrderDTO {
    private String orderCode;
    private String status;

    public OrderDTO(String orderCode, String status) {
        this.orderCode = orderCode;
        this.status = status;
    }
}
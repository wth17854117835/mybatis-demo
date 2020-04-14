package com.ritian.mybatis.pojo;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 14:03
 * @description:
 **/
@Data
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Double totalPrice;
    private Integer status;
    private Item item;
}

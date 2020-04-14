package com.ritian.mybatis.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 11:14
 * @description: 一对一查询   方法一：核心思想扩展Order对象，来完成映射；方法二：面向对象的思想，在Order对象中添加User对象。
 **/
@Data
public class Order {
    private Integer id;
    private Long userId;
    private String orderNumber;
    private Date created;
    private Date updated;
    private User user;
    //一对多查询：查询订单，查询出下单人信息并且查询出订单详情。
    private List<OrderDetail> detailList;
}

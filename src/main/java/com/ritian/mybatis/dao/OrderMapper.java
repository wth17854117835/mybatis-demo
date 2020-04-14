package com.ritian.mybatis.dao;

import com.ritian.mybatis.pojo.Order;
import com.ritian.mybatis.pojo.OrderUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 13:46
 * @description:
 **/
public interface OrderMapper {

    OrderUser queryOrderUserByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息
     * @param number
     * @return
     */
    Order queryOrderWithUserByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息及订单详情
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息及订单详情及订单详情对应的商品信息
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailItemByOrderNumber(@Param("number") String number);

    /**
     * 延迟加载
     * @param number
     * @return
     */
    Order queryOrderAndUserByOrderNumberLazy(@Param("number") String number);
}

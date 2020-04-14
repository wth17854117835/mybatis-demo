package com.ritian.mybatis.pojo;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 14:14
 * @description:
 **/
@Data
public class Item {
    private Integer id;
    private String itemName;
    private Float itemPrice;
    private String itemDetail;
}

package com.ritian.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 13:45
 * @description: 一对一查询 方法一：核心思想扩展Order对象，来完成映射
 **/
@Data
public class OrderUser extends Order {
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private Date created;
    private Date updated;
}

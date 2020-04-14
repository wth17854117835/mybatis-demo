package com.ritian.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: wangth_oup
 * @date: 2020-04-13 16:14
 * @description:
 **/
@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String created;
    private String updated;
}

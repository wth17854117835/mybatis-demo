package com.ritian.mybatis.dao;

import com.ritian.mybatis.pojo.User;

import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-04-13 16:25
 * @description:
 **/
public interface UserDao {

    /**
     * 根据id查询用户信息
     * @param id
     * @return User
     */
    User queryUserById(String id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> queryUserAll();

    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     */
    void deleteUser(String id);
}

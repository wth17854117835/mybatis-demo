package com.ritian.mybatis.dao;

import com.ritian.mybatis.impl.UserDaoImpl;
import com.ritian.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: wangth_oup
 * @date: 2020-04-13 16:34
 * @description:
 **/
public class UserDaoTest {

    private UserDao userDao;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
        // 方式1：有实现类，存在的问题：statement硬编码到java代码中
        this.userDao = new UserDaoImpl(sqlSession);
        // 方式2：动态代理Mapper实现类。 如果希望使用mybatis通过的动态代理的接口，就需要namespace中的值，和需要对应的Mapper(dao)接口的全路径一致。
//        this.userDao = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void queryUserById() {
        System.out.println(this.userDao.queryUserById("1"));
    }

    @Test
    public void queryUserAll() {
        List<User> userList = this.userDao.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId("3");
        user.setAge(16);
        user.setBirthday(new Date("1990/09/02"));
        user.setName("大鹏");
        user.setPassword("123456");
        user.setSex(1);
        user.setUserName("evan");
        this.userDao.insertUser(user);
        this.sqlSession.commit();
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setBirthday(new Date());
        user.setName("小鹏");
        user.setPassword("654321");
        user.setSex(1);
        user.setUserName("evan");
        user.setId("3");
        this.userDao.updateUser(user);
        this.sqlSession.commit();
    }

    @Test
    public void deleteUser() {
        this.userDao.deleteUser("3");
        this.sqlSession.commit();
    }
}
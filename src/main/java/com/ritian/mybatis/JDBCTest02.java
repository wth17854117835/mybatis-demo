package com.ritian.mybatis;

import com.ritian.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author: wangth_oup
 * @date: 2020-04-13 16:19
 * @description:
 **/
public class JDBCTest02 {

    /**
     * 1)配置mybatis-config.xml 全局的配置文件 (1、数据源，2、外部的mapper)
     * 2)创建SqlSessionFactory
     * 3)通过SqlSessionFactory创建SqlSession对象
     * 4)通过SqlSession操作数据库 CRUD
     * 5)调用session.commit()提交事务
     * 6)调用session.close()关闭会话
     */
    public static void main(String[] args) throws Exception {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("tb_user.selectUser", 1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }
}

package com.ritian.mybatis.dao;

import com.ritian.mybatis.pojo.Order;
import com.ritian.mybatis.pojo.OrderUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author: wangth_oup
 * @date: 2020-04-14 13:52
 * @description:
 **/
public class OrderMapperTest {

    private OrderMapper orderMapper;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession(true);

        // 1. 映射文件的命名空间（namespace）必须是mapper接口的全路径
        // 2. 映射文件的statement的id必须和mapper接口的方法名保持一致
        // 3. Statement的resultType必须和mapper接口方法的返回类型一致
        // 4. statement的parameterType必须和mapper接口方法的参数类型一致（不一定）
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void queryOrderUserByOrderNumber() {
        OrderUser orderUser = orderMapper.queryOrderUserByOrderNumber("202004140001");
        System.out.println(orderUser);
    }

    @Test
    public void queryOrderWithUserByOrderNumber() {
        Order order = orderMapper.queryOrderWithUserByOrderNumber("202004140001");
        System.out.println(order.getUser());
    }

    /**
     * 一对多
     */
    @Test
    public void queryOrderWithUserAndDetailByOrderNumber() throws Exception {
        Order order = orderMapper.queryOrderWithUserAndDetailByOrderNumber("202004140001");
        System.out.println(order.getUser());
        System.out.println(order.getDetailList());
    }

    /**
     * 多对多
     */
    @Test
    public void queryOrderWithUserAndDetailItemByOrderNumber() throws Exception {
        Order order = orderMapper.queryOrderWithUserAndDetailItemByOrderNumber("202004140001");
        System.out.println(order);
        System.out.println(order.getUser());
        System.out.println(order.getDetailList());
    }

    /**
     * 懒加载
     */
    @Test
    public void queryOrderAndUserByOrderNumberLazy() throws Exception {
//        Order order = orderMapper.queryOrderAndUserByOrderNumberLazy("202004140001");
//        System.out.println(order);
        //开启懒加载
        Order order = orderMapper.queryOrderAndUserByOrderNumberLazy("202004140001");
        System.out.println(order.getOrderNumber());
        System.out.println("======================");
        System.out.println(order.getUser());
    }
}
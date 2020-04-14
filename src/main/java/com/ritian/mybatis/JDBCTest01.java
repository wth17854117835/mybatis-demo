package com.ritian.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: wangth_oup
 * @date: 2020-04-13 15:48
 * @description: JDBC基础代码
 **/
public class JDBCTest01 {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver"); // 缺点：1)每次加载连接 2)驱动名称硬编码
            // 2.获取连接
            String url = "jdbc:mysql://localhost:3306/mybatis";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url,username,password); // 缺点：1)每次都要获取连接 2)连接信息硬编码
            // 3.获取statement，preparedStatement
            String sql = "select * from tb_user where id=?";
            preparedStatement = connection.prepareStatement(sql); // 缺点：sql和java代码耦合
            // 4.设置参数
            preparedStatement.setLong(1, 1l); // 缺点：1)参数类型需要手动判断 2)需要判断下标 3)手动设置参数
            // 5.执行查询
            resultSet = preparedStatement.executeQuery();
            // 6.处理结果集
            while (resultSet.next()) { // 缺点：1)结果集中的数据类型需要手动判断 2)下标或列名需要手动判断
                System.out.println(resultSet.getString("user_name"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("age"));
                System.out.println(resultSet.getDate("birthday"));
            }

        } finally {
            // 7.关闭连接，释放资源
            // 缺点：每次都要打开或关闭连接，浪费资源
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

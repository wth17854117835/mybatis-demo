package com.ritian.mybatis.dao;

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
 * @date: 2020-04-13 17:03
 * @description:
 **/
public class UserMapperTest {

    private UserMapper userMapper;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        // 指定配置文件
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
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void login() {
        System.out.println(this.userMapper.login01("wangth", "123456"));
        System.out.println(this.userMapper.login02("wangth", "123456"));
        System.out.println(this.userMapper.login03("wangth", "123456"));
    }

    @Test
    public void queryUserByTableName() {
        List<User> userList = this.userMapper.queryUserByTableName("tb_user");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserById() {
        System.out.println(this.userMapper.queryUserById(1l));
        // 一级缓存默认是开启的，并且一直无法关闭
        // 一级缓存满足条件： 1、同一个session中 2、相同的SQL和参数
//        sqlSession.clearCache();// 可以强制清除缓存
        // 执行update、insert、delete的时候，会清空缓存
        User user=new User();
        user.setId("1");
        user.setAge(25);
        userMapper.updateUser(user);

        System.out.println(this.userMapper.queryUserById(1l));
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testCache() {
        System.out.println(this.userMapper.queryUserById(1l));

        sqlSession.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.out.println(mapper.queryUserById(1l));
    }

    @Test
    public void queryUserAll() {
        List<User> userList = this.userMapper.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId("3");
        user.setAge(20);
        user.setBirthday(new Date());
        user.setName("大神");
        user.setPassword("123456");
        user.setSex(2);
        user.setUserName("bigGod");
        this.userMapper.insertUser(user);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId("3");
        user.setName("小神");
        this.userMapper.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        this.userMapper.deleteUserById(3l);
    }

    @Test
    public void testqueryUserList() {
        List<User> users = this.userMapper.queryUserList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListByNameOrAge() throws Exception {
        List<User> users = this.userMapper.queryUserListByNameOrAge("王天昊", null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListByNameAndAge() throws Exception {
        List<User> users = this.userMapper.queryUserListByNameAndAge("王", 23);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListByIds() throws Exception {
        List<User> users = this.userMapper.queryUserListByIds(new String[]{"1","2"});
        for (User user : users) {
            System.out.println(user);
        }
    }
}
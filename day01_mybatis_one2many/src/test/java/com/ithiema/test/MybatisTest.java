package com.ithiema.test;


import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlsession;
    private IUserDao userDao;
    @Before
    public void init() throws Exception{
        //    读取配置文件
        in=Resources.getResourceAsStream("SqlMapConfig.xml");
        //    创建SqlSessionfactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //    使用工厂生产SqlSession对象
        sqlsession=factory.openSession();
        //    使用Sqlsession创建dao接口的代理对象
        userDao=sqlsession.getMapper(IUserDao.class);
    }
    @After
    public void destory() throws Exception{
        //    6.释放资源
        //    提交事务
        sqlsession.commit();
        sqlsession.close();
        in.close();
    }


    @Test
    public void testFindAll(){

    //    使用代理对象执行方法
        List<User> user=userDao.findAll();
        for(User user1:user){
            System.out.println(user1);
        }

    }
    @Test
    public void testSave(){
    User user=new User();
    user.setUsername("mybatis_last");
    user.setAddress("河南");
    user.setSex("男");
    user.setBirthday(new Date());
    System.out.println("保存操作之前"+user);
    userDao.saveUser(user);
//    提交事务
//    sqlsession.commit();
    System.out.println("保存操作之后"+user);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void updateSave(){
        User user=new User();
        user.setId(1);
        user.setUsername("updatemybatis");
        user.setAddress("henan");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.updateUser(user);
//    提交事务
        sqlsession.commit();
    }
    @Test
    public void deleteUser(){
        User user=new User();


        userDao.deleteUser(1);

    }

    @Test
    public void selectUser(){
        User user= userDao.findbyid(2);
        System.out.print("use id to select");
        System.out.println(user);

    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        List<User> users= userDao.findByName("%mybatis%");
        for (User user:users){
            System.out.println("模糊查询");
            System.out.println(user);
        }
    }



    @Test
    public void testFindByCondition(){
        User u=new User();
        u.setUsername("mybatis");
        u.setSex("女");
        List<User> users=userDao.findUserByCondition(u);
        for(User user:users){
            System.out.println(user);
        }
    }







}

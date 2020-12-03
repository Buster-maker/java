package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {
/**
 *查询所有操作
 */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);
    /**
     * 改操作
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param userId
     */
    void deleteUser(Integer userId);
    /**
     * 查询一个用户
     * @return
     */
    User findbyid(Integer userId);
    /**
     * 模糊查询
     */
    List<User> findByName(String username);


    /**
     * 根据传入参数条件
     */
    List<User> findUserByCondition(User user);


}

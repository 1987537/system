package com.shopping.service;

import com.shopping.pojo.User;

import java.util.List;

public interface UserService {
    //登录查询
    User queryUserByUsernameAndPwd(User user);
    //根据uid查询余额
    int queryBalanceByUid(int uid);
    //扣除余额
    int deductBalanceByUid(User user);
    //增加余额
    int addBalanceByUid(User user);
    //根据uid查询用户和借阅记录
    User queryUserByUid(int uid);
    //查询所有用户
    List<User> queryAllUser();
    //根据uid查询用户
    User queryOneUserByUid(int uid);
    //添加用户
    int addUser(User user);
    //修改用户
    int updateUserByUid(User user);
    //删除用户
    int deleteUserByUid(int uid);
}

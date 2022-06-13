package com.shopping.service.impl;

import com.shopping.mapper.UserMapper;
import com.shopping.pojo.User;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByUsernameAndPwd(User user) {
        return userMapper.queryUserByUsernameAndPwd(user);
    }

    @Override
    public int queryBalanceByUid(int uid) {
        return userMapper.queryBalanceByUid(uid);
    }

    @Override
    public int deductBalanceByUid(User user) {
        return userMapper.deductBalanceByUid(user);
    }

    @Override
    public int addBalanceByUid(User user) {
        return userMapper.addBalanceByUid(user);
    }

    @Override
    public User queryUserByUid(int uid) {
        return userMapper.queryUserByUid(uid);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public User queryOneUserByUid(int uid) {
        return userMapper.queryOneUserByUid(uid);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserByUid(User user) {
        return userMapper.updateUserByUid(user);
    }

    @Override
    public int deleteUserByUid(int uid) {
        return userMapper.deleteUserByUid(uid);
    }

    @Override
    public List<User> queryUserTop() {
        return userMapper.queryUserTop();
    }
}

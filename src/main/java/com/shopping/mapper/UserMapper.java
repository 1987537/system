package com.shopping.mapper;

import com.shopping.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface  UserMapper {
    //登录时查询是否有这个用户
    @Select("SELECT * FROM `user` WHERE `username`=#{username} AND `password`=#{password}")
    User queryUserByUsernameAndPwd(User user);

    //根据uid查询余额
    @Select("SELECT `balance` FROM `user` WHERE `uid`=#{uid};")
    int queryBalanceByUid(int uid);

    //扣除余额
    @Update("UPDATE `user` SET `balance`=`balance`-#{penalty} WHERE `uid`=#{uid}")
    int deductBalanceByUid(User user);

    //增加余额
    @Update("UPDATE `user` SET `balance`=`balance`+#{topup} WHERE `uid`=#{uid}")
    int addBalanceByUid(User user);

    //根据uid查用户和借书记录
    @Select("SELECT * FROM `user` WHERE `uid`=#{uid}")
    @Results({
            @Result(id = true, column = "uid", property = "uid"),
            @Result(column = "uid", property = "borrowBookList",
                    many = @Many(select = "com.shopping.mapper.BorrowbookMapper.queryBorrowByUid"))

    })
    User queryUserByUid(int uid);

    //查询所有用户
    @Select("SELECT * FROM `user`")
    List<User> queryAllUser();

    //根据uid查询用户
    @Select("SELECT * FROM `user` WHERE `uid`=#{uid}")
    User queryOneUserByUid(int uid);

    //注册添加用户
    @Insert("INSERT INTO user(username,password,balance,phone) values(#{username},#{password},#{balance},#{phone})")
    int addUser(User user);

    //修改用户
    @Update("UPDATE `user` SET `username`=#{username},`password`=#{password},`phone`=#{phone} WHERE `uid`=#{uid}")
    int updateUserByUid(User user);

    //删除用户
    @Delete("DELETE FROM `user` WHERE `uid`=#{uid}")
    int deleteUserByUid(int uid);

    //余额排行榜
    @Select("SELECT `uid`,`username`,`balance` FROM `user` WHERE `identity`=2 ORDER BY `balance` DESC LIMIT 3")
    List<User> queryUserTop();

}

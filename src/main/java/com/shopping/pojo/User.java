package com.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class User {
    private Integer uid;
    private String username;
    private Integer identity;
    private String password;
    private Integer balance;
    private String phone;
    private Integer penalty;//罚款
    private Integer topup;//充值
    private List<BorrowBook> borrowBookList;
    private String code;//验证码
}

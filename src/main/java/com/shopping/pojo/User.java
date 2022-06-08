package com.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class User {
    private int uid;
    private String username;
    private int identity;
    private String password;
    private int balance;
    private int phone;
}

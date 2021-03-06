package com.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class BorrowBook {
    private int bid;
    private int uid;
    private Date loandate;
    private Date returndate;
    private Book book;
    private Date startIndex;//开始查询的时间
    private Date endIndex;//结束查询的时间
}

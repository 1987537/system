package com.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class Book {
    private int bid;
    private String bname;
    private String pic;
    private int cid;
    private String publishing;
    private int total;
    private int borrow;
    private int residue;
}

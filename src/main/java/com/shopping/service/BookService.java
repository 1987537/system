package com.shopping.service;

import com.shopping.pojo.Book;
import com.shopping.pojo.Catalogue;

import java.util.List;

public interface BookService {
    //根据bid查
    Book queryBookBybid(int bid);
    //归还书时对数量进行更新
    int updateBookReturnByBid(int bid);
    //借书时对数量进行更新
    int updateBookBorrowByBid(int bid);
    //增
    int addBook(Book book);
    //删
    int deleteBookById(int bid);
    //改
    int updateBookById(Book book);
    //修改数量关系
    int updateBorrowAndReturn(Book book);
    //全查
    List<Book> queryListBook();
    //根据cid统计此目录下书的本数
    Catalogue queryBookCountByCid(int cid);
}

package com.shopping.service.impl;

import com.shopping.mapper.BookMapper;
import com.shopping.pojo.Book;
import com.shopping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public Book queryBookBybid(int bid) {
        return bookMapper.queryBookBybid(bid);
    }

    @Override
    public int updateBookReturnByBid(int bid) {
        return bookMapper.updateBookReturnByBid(bid);
    }

    @Override
    public int updateBookBorrowByBid(int bid) {
        return bookMapper.updateBookBorrowByBid(bid);
    }

    @Override
    public int updateBorrowAndReturn(Book book) {
        return bookMapper.updateBorrowAndReturn(book);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBookById(int bid) {
        return bookMapper.deleteBookById(bid);
    }

    @Override
    public int updateBookById(Book book) {
        return bookMapper.updateBookById(book);
    }

    @Override
    public List<Book> queryListBook() {
        return bookMapper.queryListBook();
    }
}

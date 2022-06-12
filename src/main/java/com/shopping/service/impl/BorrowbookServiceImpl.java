package com.shopping.service.impl;

import com.shopping.mapper.BorrowbookMapper;
import com.shopping.pojo.BorrowBook;
import com.shopping.pojo.RankingTop;
import com.shopping.service.BorrowbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BorrowbookServiceImpl implements BorrowbookService {

    @Autowired
    private BorrowbookMapper borrowbookMapper;

    @Override
    public List<BorrowBook> queryAllBorrow() {
        return borrowbookMapper.queryAllBorrow();
    }

    @Override
    public List<BorrowBook> queryBorrowByUid(int uid) {
        return borrowbookMapper.queryBorrowByUid(uid);
    }

    @Override
    public void returnBook(BorrowBook borrowBook) {
        borrowbookMapper.returnBook(borrowBook);
    }

    @Override
    public int overdue(BorrowBook borrowBook) {
        return borrowbookMapper.overdue(borrowBook);
    }

    @Override
    public List<RankingTop> queryTop() {
        return borrowbookMapper.queryTop();
    }

    @Override
    public int addBorrowbook(BorrowBook borrowBook) {
        return borrowbookMapper.addBorrowbook(borrowBook);
    }

    @Override
    public List<BorrowBook> queryUserIsReturnByUid(int uid) {
        return borrowbookMapper.queryUserIsReturnByUid(uid);
    }

    @Override
    public BorrowBook queryUserAndBookIsNull(BorrowBook borrowBook) {
        return borrowbookMapper.queryUserAndBookIsNull(borrowBook);
    }

    @Override
    public List<BorrowBook> queryOneMonthBorrow(){
        return borrowbookMapper.queryOneMonthBorrow();
    };

    @Override
    public List<BorrowBook> queryMangMonthNoReturnBorrow(){
        return borrowbookMapper.queryMangMonthNoReturnBorrow();
    };

    @Override
    public List<BorrowBook> queryListByTimeBorrow(BorrowBook borrowBook){
        return borrowbookMapper.queryListByTimeBorrow(borrowBook);
    };


    @Override
    public List<BorrowBook> queryUserOneMonthBorrow(int uid){
        return borrowbookMapper.queryUserOneMonthBorrow(uid);
    };



    @Override
    public List<BorrowBook> queryUserMangMonthNoReturnBorrow(int uid) {
        return borrowbookMapper.queryUserMangMonthNoReturnBorrow(uid);
    }

    @Override
    public List<BorrowBook> queryUserListByTimeBorrow(BorrowBook borrowBook){
        return borrowbookMapper.queryUserListByTimeBorrow(borrowBook);
    };

}

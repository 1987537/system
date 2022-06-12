package com.shopping.service;

import com.shopping.pojo.BorrowBook;
import com.shopping.pojo.RankingTop;

import java.util.List;

public interface BorrowbookService {
    //查询所有借阅信息
    List<BorrowBook> queryAllBorrow();

    //根据用户id查询借阅信息
    List<BorrowBook> queryBorrowByUid(int uid);

    //归还图书
    void returnBook(BorrowBook borrowBook);

    //查看相隔天数
    int overdue(BorrowBook borrowBook);

    //排行榜
    List<RankingTop> queryTop();

    //增加借书记录
    int addBorrowbook(BorrowBook borrowBook);

    //检测user是否借阅图书未归还
    List<BorrowBook> queryUserIsReturnByUid(int uid);

    //检测是否借了同一本书
   BorrowBook queryUserAndBookIsNull(BorrowBook borrowBook);
    //管理员查询近一个月的借书记录
    List<BorrowBook> queryOneMonthBorrow();

    //管理员查询超过一个月没有归还图书的借书记录
    List<BorrowBook> queryMangMonthNoReturnBorrow();

    //管理员查询在一个时间段内的借书记录
    List<BorrowBook> queryListByTimeBorrow(BorrowBook borrowBook);


    //用户查询近一个月的借书记录
    List<BorrowBook> queryUserOneMonthBorrow(int uid);

    //用户查询超过一个月没有归还图书的借书记录
    List<BorrowBook> queryUserMangMonthNoReturnBorrow(int uid);

    //用户查询在一个时间段内的借书记录
    List<BorrowBook> queryUserListByTimeBorrow(BorrowBook borrowBook);
}

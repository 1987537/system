package com.shopping.mapper;

import com.shopping.pojo.BorrowBook;
import com.shopping.pojo.RankingTop;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BorrowbookMapper {
    //增加借书记录
    @Insert("INSERT `borrowbook`(`bid`,`uid`,`loandate`) VALUES(#{bid},#{uid},#{loandate})")
    int addBorrowbook(BorrowBook borrowBook);

    //管理员查询近一个月的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE (CURDATE()-`loandate`)<=30")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryOneMonthBorrow();

    //管理员查询超过一个月没有归还图书的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE (CURDATE()-`loandate`)>30 AND `returndate` IS NULL ")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryMangMonthNoReturnBorrow();

    //管理员查询在一个时间段内的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE `loandate`>=#{startIndex} AND `loandate`<=#{endIndex}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryListByTimeBorrow(BorrowBook borrowBook);


    //用户查询近一个月的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE (CURDATE()-`loandate`)<=30 and`uid`=#{uid}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryUserOneMonthBorrow(int uid);

    //用户查询超过一个月没有归还图书的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE (CURDATE()-`loandate`)>30 AND `returndate` IS NULL and `uid`=#{uid}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryUserMangMonthNoReturnBorrow(int uid);

    //用户查询在一个时间段内的借书记录
    @Select("SELECT * FROM `borrowbook` WHERE `loandate`>=#{startIndex} AND `loandate`<=#{endIndex} and `uid`=#{uid}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryUserListByTimeBorrow(BorrowBook borrowBook);

    //查询所有借阅信息
    @Select("SELECT * FROM `borrowbook`")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryAllBorrow();

    //检测是否借了同一本书
    @Select("SELECT * FROM `borrowbook` WHERE `uid`=#{uid} AND `bid`=#{bid} AND `loandate` IS NOT NULL AND `returndate` IS NULL")
    BorrowBook queryUserAndBookIsNull(BorrowBook borrowBook);
    //检测user是否借阅图书未归还
    @Select("SELECT * FROM `borrowbook` WHERE `uid`=#{uid} AND `loandate` IS NOT NULL AND `returndate` IS NULL")
    List<BorrowBook> queryUserIsReturnByUid(int uid);

    //根据uid查询借阅信息
    @Select("SELECT * FROM `borrowbook` WHERE `uid`=#{uid}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<BorrowBook> queryBorrowByUid(int uid);

    //归还图书
    @Update("UPDATE `borrowbook` SET `returndate`=CURDATE() WHERE `bid`=#{bid} AND `uid`=#{uid}")
    void returnBook(BorrowBook borrowBook);

    //查询相隔天数
    @Select("SELECT `returndate`-`loandate` FROM `borrowbook` WHERE `bid`=#{bid} AND `uid`=#{uid}")
    int overdue(BorrowBook borrowBook);

    //排行榜
    @Select("SELECT `bid`,COUNT(*) AS \"count\" FROM `borrowbook` GROUP BY `bid` ORDER BY COUNT(*) DESC LIMIT 5")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.shopping.mapper.BookMapper.queryBookBybid"))

    })
    List<RankingTop> queryTop();
}

package com.shopping.mapper;

import com.shopping.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookMapper {
    //根据bid查
    @Select("SELECT * FROM `book` WHERE `bid`=#{bid}")
    Book queryBookBybid(int bid);
    //归还书时对数量进行更新
    @Update("UPDATE `book` SET `residue`=`residue`+1,`borrow`=`total`-`residue` WHERE `bid`=#{bid}")
    int updateBookReturnByBid(int bid);
    //借书时对数量进行更新
    @Update("UPDATE `book` SET `residue`=`residue`-1,`borrow`=`total`-`residue` WHERE `bid`=#{bid}")
    int updateBookBorrowByBid(int bid);
    //根据cid查
    @Select("SELECT * FROM `book` WHERE `cid`=#{cid}")
    List<Book> queryBookByCid(int cid);
    //增
    @Insert("INSERT INTO `book`(`bname`,`pic`,`cid`,`publishing`,`total`,`borrow`,`residue`) VALUES(#{bname},#{pic},#{cid},#{publishing},#{total},#{borrow},#{residue})")
    int addBook(Book book);
    //删
    @Delete("DELETE FROM `book` WHERE `bid`=#{bid}")
    int deleteBookById(int bid);
    //改
    @Update("UPDATE `book` SET `bname`=#{bname},`pic`=#{pic},`cid`=#{cid},`publishing`=#{publishing} ,`total`=#{total} where `bid`=#{bid}")
    int updateBookById(Book book);
    //修改数量关系
    @Update("UPDATE `book` SET `residue`=#{total}-#{borrow} WHERE `bid`=#{bid}")
    int updateBorrowAndReturn(Book book);
    //全查
    @Select("SELECT * FROM `book`")
    List<Book> queryListBook();
}

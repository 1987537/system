package com.shopping.mapper;

import com.shopping.pojo.Catalogue;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface CatalogueMapper {
    //增
    @Update("INSERT INTO `catalogue`(`cname`) VALUES(#{cname})")
    int addCat(Catalogue catalogue);
    //删
    @Delete("DELETE FROM `catalogue` WHERE `cid`=#{cid}")
    int deleteCatById(int cid);
    //改
    @Update("UPDATE `catalogue` SET `cname`=#{cname} where `cid`=#{cid}")
    int updateCatById(Catalogue catalogue);
    //根据cid查
    @Select("SELECT * FROM `catalogue` WHERE `cid`=#{cid}")
    Catalogue queryCatByCid(int cid);
    //全查
    @Select("SELECT * FROM `catalogue`")
    List<Catalogue> queryListCat();
    //根据id查图书
    @Select("select * from catalogue where cid=#{cid}")
    @Results({
            @Result(id=true,column = "cid",property = "cid"),
            @Result(column = "cid",property = "books",
                    many=@Many(select = "com.shopping.mapper.BookMapper.queryBookByCid"))

    })
    Catalogue queryLisyByCid(int cid);
}

package com.shopping.controller;

import com.shopping.pojo.Book;
import com.shopping.pojo.Catalogue;
import com.shopping.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("cat")
public class CatalogueController {
    @Autowired
    private CatalogueService catalogueService;
    //管理员
    //跳转添加页
    @RequestMapping("redAdd")
    public String redAdd(){
        return "admin/Catalogue/addCat";
    }

    //添加类别
    @RequestMapping("addCat")
    public String addCat(Catalogue catalogue){
        int res=catalogueService.addCat(catalogue);
        if(res!=0){
            return "redirect:/cat/queryListCat.action";
        }else{
            return "error";
        }
    }
    //删除类别
    @RequestMapping("deleteCatById")
    public String deleteCatById(int cid){
        List<Book> books=catalogueService.queryLisyByCid(cid).getBooks();
        if(books!=null){
            return "error/deleteCatById";
        }
        int res=catalogueService.deleteCatById(cid);
        if(res!=0){
            return "redirect:/cat/queryListCat.action";
        }else{
            return "error";
        }
    }
    //修改类别
    @RequestMapping("updateCatById")
    public String updateCatById(Catalogue catalogue){
        int res= catalogueService.updateCatById(catalogue);
        if(res!=0){
            return "redirect:/cat/queryListCat.action";
        }else {
            return "error";
        }
    }
    //根据Cid查询类别名
    @RequestMapping("queryCatByCid")
    public ModelAndView queryCatByCid(int cid){
        Catalogue catalogue=catalogueService.queryCatByCid(cid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("catalogue",catalogue);
        mv.setViewName("admin/Catalogue/editCat");
        return mv;
    }
    //查询目录列表
    @RequestMapping("queryListCat")
    public ModelAndView queryListCat(){
        List<Catalogue> catalogueList=catalogueService.queryListCat();
        ModelAndView mv=new ModelAndView();
        mv.addObject("catalogueList",catalogueList);
        mv.setViewName("admin/Catalogue/catalogueList");
        return mv;
    }
    //查询同一类的书
    @RequestMapping("queryListByCid")
    public ModelAndView queryListByCid(int cid){
        List<Book> bookList= catalogueService.queryLisyByCid(cid).getBooks();
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookList",bookList);
        mv.setViewName("admin/Book/booklist");
        return mv;
    }


    //用户
    //根据Cid查询类别名
    @RequestMapping("userqueryCatByCid")
    public ModelAndView userqueryCatByCid(int cid,int uid){
        Catalogue catalogue=catalogueService.queryCatByCid(cid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("catalogue",catalogue);
        mv.addObject("uid",uid);
        mv.setViewName("Catalogue/catalogueList");
        return mv;
    }
    //查询目录列表
    @RequestMapping("userqueryListCat")
    public ModelAndView userqueryListCat(int uid){
        List<Catalogue> catalogueList=catalogueService.queryListCat();
        ModelAndView mv=new ModelAndView();
        mv.addObject("catalogueList",catalogueList);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Catalogue/catalogueList");
        return mv;
    }
    //查询同一类的书
    @RequestMapping("userqueryListByCid")
    public ModelAndView userqueryListByCid(int cid,int uid){
        List<Book> bookList= catalogueService.queryLisyByCid(cid).getBooks();
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookList",bookList);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Book/booklist");
        return mv;
    }

}

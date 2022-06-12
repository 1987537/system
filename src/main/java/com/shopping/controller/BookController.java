package com.shopping.controller;

import com.shopping.pojo.Book;
import com.shopping.pojo.Catalogue;
import com.shopping.service.BookService;
import com.shopping.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CatalogueService catalogueService;

    //管理员
    //跳转到增加图书
    @RequestMapping("redAddBook")
    public ModelAndView redAddBook(){
        List<Catalogue> catalogueList=catalogueService.queryListCat();
        ModelAndView mv=new ModelAndView();
        mv.addObject("catalogueList",catalogueList);
        mv.setViewName("admin/Book/addbook");
        return mv;
    }

    //增加图书
    @RequestMapping("/addBook")
    public String addBook(Book book,MultipartFile items_pic){
        String originalFilename = items_pic.getOriginalFilename();
        book.setPic(originalFilename);
        book.setResidue(book.getTotal());
        System.out.println(book);
        int res=bookService.addBook(book);
        if(res!=0){
            return "redirect:/books/queryListBook.action";
        }else{
            return "error/addBookerror";
        }
    }
    //删除图书
    @RequestMapping("/deleteBookById")
    public String deleteBookById(int bid){
        //查询bid这本书是否有借出在外的情况
        Book book=bookService.queryBookBybid(bid);
        if(book.getBorrow()!=0){//存在书借在外情况，不可删除
            return "error/ErrordeleteBookById";
        }else{
            int res=bookService.deleteBookById(bid);
            if(res!=0){
                return "redirect:/books/queryListBook.action";
            }else{
                return "error/ErrordeleteBookById";
            }
        }
    }
    //更新图书
    @RequestMapping("/updateBookById")
    public String updateBookById(Book book,MultipartFile items_pic){
        String originalFilename = items_pic.getOriginalFilename();
        book.setPic(originalFilename);
        bookService.updateBorrowAndReturn(book);
        int res= bookService.updateBookById(book);
        if(res!=0){
            return "redirect:/books/queryListBook.action";
        }else{
            return "error/updateBookByIdError";
        }
    }
    //查询图书根据bid
    @RequestMapping("/queryBookBybid")
    public ModelAndView queryBookBybid(int bid){
        Book book=bookService.queryBookBybid(bid);
        List<Catalogue> catalogueList=catalogueService.queryListCat();
        ModelAndView mv=new ModelAndView();
        mv.addObject("book",book);
        mv.addObject("catalogueList",catalogueList);
        mv.setViewName("admin/Book/editbook");
        return mv;
    }
    //返回图书列表
    @RequestMapping("/queryListBook")
    public ModelAndView queryListBook(){
        List<Book> bookList=bookService.queryListBook();
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookList",bookList);
        mv.setViewName("admin/Book/booklist");
        return mv;
    }

    //用户
    //返回图书列表
    @RequestMapping("/userqueryListBook")
    public ModelAndView userqueryListBook(int uid){
        List<Book> bookList=bookService.queryListBook();
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookList",bookList);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Book/booklist");
        return mv;
    }
    //查询图书根据bid
    @RequestMapping("/userqueryBookBybid")
    public ModelAndView userqueryBookBybid(int bid,int uid){
        Book book=bookService.queryBookBybid(bid);
        List<Catalogue> catalogueList=catalogueService.queryListCat();
        ModelAndView mv=new ModelAndView();
        mv.addObject("books",book);
        mv.addObject("catalogueList",catalogueList);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Book/book");
        return mv;
    }

}

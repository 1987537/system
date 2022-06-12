package com.shopping.controller;

import com.shopping.pojo.BorrowBook;
import com.shopping.pojo.RankingTop;
import com.shopping.service.BookService;
import com.shopping.service.BorrowbookService;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("borrow")
public class BorrowbookController {
    @Autowired
    private BorrowbookService borrowbookService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    //管理员
    //根据时间段查询借阅信息
    @RequestMapping("queryListByTimeBorrow")
    public ModelAndView queryListByTimeBorrow(BorrowBook borrowBook){
        ModelAndView mv=new ModelAndView();
        if(borrowBook.getStartIndex().compareTo(borrowBook.getEndIndex())>0){
            mv.setViewName("error/queryListByTimeBorrow");
        }else{
            List<BorrowBook> borrowBookList=borrowbookService.queryListByTimeBorrow(borrowBook);
            mv.addObject("borrowBookList",borrowBookList);
            mv.setViewName("admin/Borrow/queryBorrow");
        }
        return mv;
    }
    //查询借阅信息
    @RequestMapping("queryAllBorrow")
    public ModelAndView queryAllBorrow(){
        //近一个月借书记录
        List<BorrowBook> borrowBookList=borrowbookService.queryOneMonthBorrow();
        //超出一个月未归还记录
        List<BorrowBook> borrowBookListsuper=borrowbookService.queryMangMonthNoReturnBorrow();
        ModelAndView mv=new ModelAndView();
        mv.addObject("borrowBookList",borrowBookList);
        mv.addObject("borrowBookListsuper",borrowBookListsuper);
        mv.addObject("newdate",new Date());
        mv.setViewName("admin/Borrow/AllBorrow");
        return mv;
    }

    //排行榜
    @RequestMapping("queryTopAdmin")
    public ModelAndView queryTopAdmin(){
        List<RankingTop> rankingTop=borrowbookService.queryTop();
        String[] bookName=new String[5];
        int[] borrowcount=new int[5];
        int[] bookresidue=new int[5];
        int index=0;
        for (RankingTop r:rankingTop) {
            bookName[index]=r.getBook().getBname();
            borrowcount[index]=r.getCount();
            bookresidue[index]=r.getBook().getResidue();
            index++;
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookName",bookName);
        mv.addObject("borrowcount",borrowcount);
        mv.addObject("bookresidue",bookresidue);
        mv.setViewName("admin/Borrow/BorrowTop");
        return mv;
    }


    //用户
    //根据一段时间查询
    @RequestMapping("queryUserListByTimeBorrow")
    public ModelAndView queryUserListByTimeBorrow(BorrowBook borrowBook){
        ModelAndView mv=new ModelAndView();
        if(borrowBook.getStartIndex().compareTo(borrowBook.getEndIndex())>0){
            mv.addObject("uid",borrowBook.getUid());
            mv.setViewName("error/queryUserListByTimeBorrow");
        }else{
            List<BorrowBook> borrowBookList=borrowbookService.queryUserListByTimeBorrow(borrowBook);
            mv.addObject("borrowBookList",borrowBookList);
            mv.addObject("uid",borrowBook.getUid());
            mv.setViewName("consumer/Borrow/queryBorrow");
        }
        return mv;
    }
    //根据用户id查询借阅信息
    @RequestMapping("queryBorrowByUid")
    public ModelAndView queryBorrowByUid(int uid){
        List<BorrowBook> borrowBookList=borrowbookService.queryUserOneMonthBorrow(uid);
        List<BorrowBook> borrowBookListsuper=borrowbookService.queryUserMangMonthNoReturnBorrow(uid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("borrowBookList",borrowBookList);
        mv.addObject("borrowBookListsuper",borrowBookListsuper);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Borrow/personBorrow");
        return mv;
    }
    //归还图书
    @RequestMapping("returnBook")
    public ModelAndView returnBook(BorrowBook borrowBook){
        borrowbookService.returnBook(borrowBook);
        //归还对借出数和剩余数进行改变
        bookService.updateBookReturnByBid(borrowBook.getBid());
        //得到日数
        int res=borrowbookService.overdue(borrowBook);
        if(res<=30){
            ModelAndView mv=new ModelAndView("redirect:/borrow/queryBorrowByUid.action?uid="+borrowBook.getUid());
            return mv;
        }else{
            ModelAndView mv=new ModelAndView();
            int dedute=res-30;//逾期一天缴纳一块
            int balance=userService.queryBalanceByUid(borrowBook.getUid());
            mv.addObject("dedute",dedute);
            mv.addObject("balance",balance);
            mv.addObject("uid",borrowBook.getUid());
            mv.setViewName("consumer/Borrow/overdue");
            return mv;
        }
    }
    //排行榜
    @RequestMapping("queryTop")
    public ModelAndView queryTop(int uid){
        List<RankingTop> rankingTop=borrowbookService.queryTop();
        String[] bookName=new String[5];
        int[] borrowcount=new int[5];
        int[] bookresidue=new int[5];
        int index=0;
        for (RankingTop r:rankingTop) {
            bookName[index]=r.getBook().getBname();
            borrowcount[index]=r.getCount();
            bookresidue[index]=r.getBook().getResidue();
            index++;
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("bookName",bookName);
        mv.addObject("borrowcount",borrowcount);
        mv.addObject("bookresidue",bookresidue);
        mv.addObject("uid",uid);
        mv.setViewName("consumer/Borrow/BorrowTop");
        return mv;
    }
    //跳转到借书页面
    @RequestMapping("toAddBorrowBook")
    public ModelAndView toAddBorrowBook(BorrowBook borrowBook){
        int flag=1;
        ModelAndView mv=new ModelAndView();
        List<BorrowBook> borrowBookList=borrowbookService.queryBorrowByUid(borrowBook.getUid());
        for (BorrowBook oldborrowBook:borrowBookList) {
            BorrowBook newb=new BorrowBook();
            newb.setUid(borrowBook.getUid());
            newb.setBid(borrowBook.getBid());
            newb.setLoandate(oldborrowBook.getLoandate());
            if(borrowbookService.queryUserAndBookIsNull(newb)!=null){
                flag=0;
                mv.addObject("uid",borrowBook.getUid());
                mv.setViewName("error/toAddBorrowBook");
            }
        }
        if(flag==1){
            mv.addObject("uid",borrowBook.getUid());
            mv.addObject("bid",borrowBook.getBid());
            mv.setViewName("consumer/Book/addBorrowBook");
        }
        return mv;
    }
    //借书
    @RequestMapping("addBorrowbook")
    public ModelAndView addBorrowbook(BorrowBook borrowBook){
        //借书时对数量进行更新
        bookService.updateBookBorrowByBid(borrowBook.getBid());
        int res=borrowbookService.addBorrowbook(borrowBook);
        ModelAndView mv=new ModelAndView();
        if(res!=0){
            mv.setViewName("redirect:/borrow/queryBorrowByUid.action?uid="+borrowBook.getUid());
        }else{
            mv.setViewName("error/error");
        }
        return mv;
    }
}

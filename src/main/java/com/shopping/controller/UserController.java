package com.shopping.controller;

import com.shopping.pojo.BorrowBook;
import com.shopping.pojo.User;
import com.shopping.service.BorrowbookService;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BorrowbookService borrowbookService;
    @RequestMapping("queryUserByUsernameAndPwd")
    public ModelAndView queryUserByUsernameAndPwd(User getuser){
        User user=userService.queryUserByUsernameAndPwd(getuser);
        System.out.println(user);
        ModelAndView mv=new ModelAndView();
        if(user!=null){
            mv.addObject("user",user);
            mv.addObject("uid",user.getUid());
            if(user.getIdentity()==1){
                mv.setViewName("admin/frame");
            }else if(user.getIdentity()==2){
                mv.setViewName("consumer/frame");
            }
        }else{
            mv.setViewName("error/ErrorqueryUserByUsernameAndPwd");
        }
        return mv;
    }
    //缴纳罚款
    @RequestMapping("deductBalanceByUid")
    public ModelAndView deductBalanceByUid(User user){
        int balance=userService.queryBalanceByUid(user.getUid());
        if(user.getPenalty()>balance){
            ModelAndView mv=new ModelAndView();
            mv.addObject("uid",user.getUid());
            mv.addObject("penalty",user.getPenalty());
            mv.setViewName("consumer/User/topup");
            return mv;
        }else{
            int res=userService.deductBalanceByUid(user);
            user.setPenalty(0);
            if(res>0){
                ModelAndView mv=new ModelAndView("redirect:/borrow/queryBorrowByUid.action?uid="+user.getUid());
                return mv;
            }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("error/ErrorqueryUserByUsernameAndPwd");
                return mv;
            }
        }
    }

    //充值
    @RequestMapping("addBalanceByUid")
    public ModelAndView addBalanceByUid(User user){
        int res=userService.addBalanceByUid(user);
        user.setTopup(0);
        ModelAndView mv=new ModelAndView();
        if(res!=0){
            if(user.getPenalty()!=0){
                mv.addObject("balance",userService.queryBalanceByUid(user.getUid()));
                mv.addObject("dedute",user.getPenalty());
                mv.addObject("uid",user.getUid());
                mv.setViewName("consumer/Borrow/overdue");
            }else{
                mv.setViewName("redirect:/users/queryOneUserByUid.action?uid="+user.getUid());
            }

        }else{
            mv.setViewName("error/ErrorqueryUserByUsernameAndPwd");
        }
        return mv;
    }
    //查询用户和借书记录
    @RequestMapping("queryUserByUid")
    public ModelAndView queryUserByUid(int uid){
        User user=userService.queryUserByUid(uid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("admin/User/personalInf");
        return mv;
    }
    //查询所有用户
    @RequestMapping("queryAllUser")
    public ModelAndView queryAllUser(){
        List<User> userList=userService.queryAllUser();
        ModelAndView mv=new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("admin/User/UserList");
        return mv;
    }
    //查询用户根据uid
    @RequestMapping("queryOneUserByUid")
    public ModelAndView queryOneUserByUid(int uid){
        User user=userService.queryOneUserByUid(uid);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        mv.addObject("uid",user.getUid());
        mv.setViewName("consumer/User/personalInf");
        return mv;
    }
    //跳转到topup.jsp
    @RequestMapping("redtopup")
    public ModelAndView redtopup(int uid){
        ModelAndView mv=new ModelAndView();
        mv.addObject("uid",uid);
        mv.addObject("penalty",0);
        mv.setViewName("consumer/User/topup");
        return mv;
    }

    //管理员
    //删
    @RequestMapping("deleteUserByUid")
    public ModelAndView deleteUserByUid(int uid){
        //查询用户是否存在未归还图书
        List<BorrowBook> borrowBookList=borrowbookService.queryUserIsReturnByUid(uid);
        int len=borrowBookList.size();
        ModelAndView mv=new ModelAndView();
        if(len!=0){//存在未归还图书
            mv.setViewName("error/errorDeleteUser");
        }else{
            int res=userService.deleteUserByUid(uid);
            if(res!=0){
                mv.setViewName("redirect:/users/queryAllUser.action");
            }else{
                mv.setViewName("error/error");
            }
        }
        return mv;
    }
    //跳转到改的页面
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(int uid){
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userService.queryUserByUid(uid));
        mv.setViewName("admin/User/updateUser");
        return mv;
    }
    //改
    @RequestMapping("updateUser")
    public String updateUser(User user){
        int res=userService.updateUserByUid(user);
        if(res!=0){
            return "redirect:/users/queryAllUser.action";
        }else{
            return "error/error";
        }
    }
    //用户
    //删
    @RequestMapping("userdeleteUserByUid")
    public ModelAndView userdeleteUserByUid(int uid){
        //查询用户是否存在未归还图书
        List<BorrowBook> borrowBookList=borrowbookService.queryUserIsReturnByUid(uid);
        int len=borrowBookList.size();
        ModelAndView mv=new ModelAndView();
        if(len!=0){//存在未归还图书
            mv.setViewName("error/errorDeleteUser");
        }else{
            int res=userService.deleteUserByUid(uid);
            if(res!=0){
                mv.setViewName("index");
            }else{
                mv.setViewName("error/error");
            }
        }
        return mv;
    }
    //跳转到改的页面
    @RequestMapping("touserUpdate")
    public ModelAndView touserUpdate(int uid){
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userService.queryUserByUid(uid));
        mv.addObject("uid",uid);
        mv.setViewName("consumer/User/updateUser");
        return mv;
    }
    //改
    @RequestMapping("updateuserUser")
    public String updateuserUser(User user){
        int res=userService.updateUserByUid(user);
        if(res!=0){
            return "redirect:/users/queryOneUserByUid.action?uid="+user.getUid();
        }else{
            return "error/error";
        }
    }


    //生成验证码
    @RequestMapping(value="/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跳转到register
    @RequestMapping("toregister")
    public String toregister(){
        return "consumer/User/register";
    }
    //添加用户
    @RequestMapping("addUser")
    public String addUser(User user, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute(RandomValidateCode.RANDOMCODEKEY));
        System.out.println(user.getCode());
        if(user.getCode().equals(session.getAttribute(RandomValidateCode.RANDOMCODEKEY))&&user.getUsername()!=null){
            int res=userService.addUser(user);
            if(res!=0){
                return "index";
            }else{
                return "error/ErrorCode";
            }
        }else{
            return "error/ErrorCode";
        }
    }

}

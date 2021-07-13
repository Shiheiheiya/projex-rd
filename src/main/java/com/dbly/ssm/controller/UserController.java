package com.dbly.ssm.controller;

import com.dbly.ssm.dao.IUserDAO;
import com.dbly.ssm.entity.User;
import com.dbly.ssm.util.Md5Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    IUserDAO iUserDAO;

    @RequestMapping("/toRegist.do")
    public String toRegist(){
        return "regist";
    }

    @RequestMapping("regist.do")
    public String regist(User user){
        user.setPwd(Md5Encrypt.md5(user.getPwd()));
        iUserDAO.addUser(user);
        return "redirect:toLogin.do";
    }

    @RequestMapping("/toLogin.do")
    public String toLogin(User user){
        return "login";
    }

    @RequestMapping("/login.do")
    public String login(User user){
        user.setPwd(Md5Encrypt.md5(user.getPwd()));
        User cur_user = iUserDAO.findUserByName(user);
        if(cur_user != null){
            if (cur_user.getAuthor() == 0) {
                return "visit_home";
            }
            else if(cur_user.getAuthor() == 1) {
                return "vip_home";
            }
            else {
                return "manage/index";
            }
        }
        else {
            return "login";
        }
    }
}

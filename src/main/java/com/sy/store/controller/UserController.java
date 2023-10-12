package com.sy.store.controller;

import com.sy.store.entity.User;
import com.sy.store.service.IUserService;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(HttpStatus.OK.value());
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User result = userService.login(username,password);
        session.setAttribute("uid",result.getUid());
        session.setAttribute("username",result.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<>(HttpStatus.OK.value(),result);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(HttpStatus.OK.value());
    }
}

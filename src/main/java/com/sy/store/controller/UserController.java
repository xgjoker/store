package com.sy.store.controller;

import com.sy.store.entity.User;
import com.sy.store.service.IUserService;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResult<User> login(String username, String password){
        User result = userService.login(username,password);
        return new JsonResult<>(HttpStatus.OK.value(),result);
    }
}

package com.sy.store.controller;

import com.sy.store.entity.BaseEntity;
import com.sy.store.entity.User;
import com.sy.store.service.IUserService;
import com.sy.store.service.ex.InsertException;
import com.sy.store.service.ex.UsernameDuplicationException;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}

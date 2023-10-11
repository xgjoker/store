package com.sy.store.controller;

import com.sy.store.service.ex.InsertException;
import com.sy.store.service.ex.ServiceException;
import com.sy.store.service.ex.UsernameDuplicationException;
import com.sy.store.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public static final int OK=200;
    
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicationException){
            result.setState(4000);
            result.setMessage(e.getMessage());
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

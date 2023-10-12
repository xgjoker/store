package com.sy.store.controller;

import com.sy.store.service.ex.*;
import com.sy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicationException){
            result.setState(4000);
            result.setMessage(e.getMessage());
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage(e.getMessage());
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(6000);
            result.setMessage(e.getMessage());
        } else if (e instanceof UserNotFoundException) {
            result.setState(7000);
            result.setMessage(e.getMessage());
        } else if (e instanceof UpdateException) {
            result.setState(8000);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}

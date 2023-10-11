package com.sy.store.service;

import com.sy.store.entity.User;
import com.sy.store.service.ex.ServiceException;
import com.sy.store.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try{
            User user = new User();
            user.setUsername("Test3");
            user.setPassword("Test1");

            userService.reg(user);
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

}

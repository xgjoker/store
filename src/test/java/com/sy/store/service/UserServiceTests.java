package com.sy.store.service;

import com.sy.store.entity.User;
import com.sy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.Consumer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try{
            User user = new User();
            user.setUsername("Test7");
            user.setPassword("Test1");

            userService.reg(user);
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
    @Test
    public void login(){
        User user = userService.login("test1","test1");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(37,"admin","Test2","Test1");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(37));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("222");
        user.setGender(1);
        userService.changeInfo(37,"Test8", user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(37,"admin","fee/po.html");
    }

    @Test
    public void test(){
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        r = ()->System.out.println("test run");
        r.run();



        Comparator<Integer> c1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        System.out.println(c1.compare(121,21));
        Comparator<Integer> c2 = (o1,o2) -> {System.out.println("test");
        return Integer.compare(o1,o2);
        };
        System.out.println(c2.compare(121,21));
        Comparator<Integer> c3 = (o1,o2) -> Integer.compare(o1,o2);
        System.out.println(c3.compare(121,21));
    }

    Consumer<Integer> con1 = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {

        }
    }
}

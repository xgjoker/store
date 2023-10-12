package com.sy.store.mapper;


import com.sy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("Test1");
        user.setPassword("Test1");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("Test1");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(8,"123","admin",new Date());
    }

    @Test
    public void findByUid(){
        User user = userMapper.findByUid(8);
        System.out.println(user);
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(37);
        user.setPhone("232");
        user.setEmail("233@gm.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
}

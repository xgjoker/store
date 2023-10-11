package com.sy.store.service.impl;

import com.sy.store.entity.User;
import com.sy.store.mapper.UserMapper;
import com.sy.store.service.IUserService;
import com.sy.store.service.ex.InsertException;
import com.sy.store.service.ex.UsernameDuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if(result!=null){
            throw new UsernameDuplicationException("user name exists");
        }

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(salt,oldPassword);
        user.setSalt(salt);
        user.setPassword(md5Password);

        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("unknown exception while inserting new user");
        }

    }

    private String getMD5Password(String password, String salt){
        for(int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;

    }
}

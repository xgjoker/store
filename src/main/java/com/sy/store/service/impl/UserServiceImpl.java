package com.sy.store.service.impl;

import com.sy.store.entity.User;
import com.sy.store.mapper.UserMapper;
import com.sy.store.service.IUserService;
import com.sy.store.service.ex.*;
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
        String md5Password = getMD5Password(oldPassword,salt);
        user.setSalt(salt);
        user.setPassword(md5Password);

        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("unknown exception while inserting new user");
        }

    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFoundException("user cannot be found");
        }

        if(result.getIsDelete()!=null && result.getIsDelete()==1){
            throw new UserNotFoundException("user name exists");
        }

        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password,salt);
        if(!newMd5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("the password is not correct");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("user not found");
        }
        String oldMd5Password = getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("wrong password");
        }
        String newMd5Password = getMD5Password(newPassword,result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("unknown update exception");
        }
    }

    private String getMD5Password(String password, String salt){
        for(int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;

    }
}

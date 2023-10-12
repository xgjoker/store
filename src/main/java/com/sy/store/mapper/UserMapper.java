package com.sy.store.mapper;

import com.sy.store.entity.User;

import java.util.Date;


public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);
}

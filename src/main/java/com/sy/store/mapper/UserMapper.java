package com.sy.store.mapper;

import com.sy.store.entity.User;


public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

}

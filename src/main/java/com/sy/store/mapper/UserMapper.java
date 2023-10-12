package com.sy.store.mapper;

import com.sy.store.entity.User;
import org.apache.ibatis.annotations.Param;

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

    Integer updateAvatarByUid(@Param("uid") Integer uid, String avatar, String modifiedUser, Date modifiedTime);
}

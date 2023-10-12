package com.sy.store.service;

import com.sy.store.entity.User;

public interface IUserService {
    void reg(User user);
    User login(String username, String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
}

package com.atm_machine_web.service;

import com.atm_machine_web.model.User;

public interface UserService {
    User findUserByUserName(String userName);
    User findUserByUserId(Long userId);
    User save(User newUser);
}

package com.atm_machine_web.service;

import com.atm_machine_web.model.User;
import com.atm_machine_web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
    @Override
    public User findUserByUserId(Long userId) {
        return userRepository.findUserByUserId(userId);
    }
    @Override
    public User save(User newUser){
        return userRepository.save(newUser);
    }
}

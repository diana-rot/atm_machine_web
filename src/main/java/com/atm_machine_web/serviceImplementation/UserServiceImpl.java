package com.atm_machine_web.serviceImplementation;

import com.atm_machine_web.model.User;
import com.atm_machine_web.repo.UserRepository;
import com.atm_machine_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

    }

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
    public List<User> findAll(){
        return userRepository.findAll();
    }
}

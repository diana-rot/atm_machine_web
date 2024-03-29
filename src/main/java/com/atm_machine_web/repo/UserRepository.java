package com.atm_machine_web.repo;

import com.atm_machine_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);

    User findUserByUserId(Long userId);

    List<User> findAll();

}

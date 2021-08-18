package com.atm_machine_web.service;

import com.atm_machine_web.model.User;
import com.atm_machine_web.repo.AtmRepository;
import com.atm_machine_web.repo.UserRepository;
import com.atm_machine_web.serviceImplementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    AtmRepository atmRepository;
    @Test

    void findUserByUserName() {
        //given
        User user = new User(
                "Mara",
                "USER",
                "111",
                "maria@a"
        );

        String userName = "Mara";
        //when
        when(userRepository.findUserByUserName(userName)).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        //then

        assertThat(user.getUserName()).isEqualTo(userName);
    }

    @Test
    void findUserByUserId() {
        //given
        User user = new User(
                "Mara",
                "USER",
                "111",
                "maria@a"
        );

        when(userRepository.findUserByUserId(user.getUserId())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
     //then
     assertThat(user.getUserName()).isEqualTo("Mara");
    }

}
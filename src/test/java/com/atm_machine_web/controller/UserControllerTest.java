package com.atm_machine_web.controller;

import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.service.AtmServiceTest;
import com.atm_machine_web.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)


class UserControllerTest {
    @Mock
    AtmServiceTest atmServiceTest;
    ModelMapper modelMapper;
    List<Notes> notes;
    List<Stacks> mockStacks;
    UserService userService;
    @Spy
    Atm atm;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        notes = new ArrayList<>();
        mockStacks = new ArrayList<>();

        atm = new Atm(1L, (float) 0);
    }
    @Test
    void addAccount() {
    }

    @Test
    void addUser() {
    }

    @Test
    void interrogateSold() {
    }

    @Test
    void show_all_users() {
    }
}
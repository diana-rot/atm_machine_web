package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.AccountsDTO;
import com.atm_machine_web.controller.dto.WithdrawResponseDTO;
import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;
import com.atm_machine_web.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    Atm atm;
    @Autowired
    UserService userService;
    @Autowired
    AccountsService accountsService;
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    StacksService stacksService;
    @Autowired
    NotesService notesService;
    @Autowired
    AtmService atmService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add_account")
    public ResponseEntity addAccount(@RequestBody Accounts newAccount) {
        Accounts accountsFromDb = accountsService.findAccountsByOwner(newAccount.getOwner());
        if (accountsFromDb == null) {
            Accounts addedAccount = accountsService.save(newAccount);
            AccountsDTO responseDTO = modelMapper.map(newAccount, AccountsDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This account already exists!");
        }
    }

    @PostMapping("/add_user")
    public ResponseEntity addUser(@RequestBody User newUser) {
        User userFromDb = userService.findUserByUserName(newUser.getUserName());
        if (userFromDb == null) {
            User addedUser = userService.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(addedUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username already exists!");
        }
    }
}

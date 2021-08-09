package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.*;
import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;
import com.atm_machine_web.service.*;
import com.atm_machine_web.service.AtmService;
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
    public ResponseEntity addAccount(@RequestBody NewAccountDTO dto) {
        User owner = userService.findUserByUserName(dto.getUserName());
        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        if (accountsFromDb == null) {
            Accounts newAccount = new Accounts(owner, dto.getSold(), dto.getCurrencyType());
            accountsService.save(newAccount);
            NewAccountDTO responseDTO = modelMapper.map(newAccount, NewAccountDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This account already exists!");
        }
    }

    @PostMapping("/add_user")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {

        User userFromDb = userService.findUserByUserName(userDTO.getUserName());
        if (userFromDb == null) {
            User newUser = new User(userDTO.getUserName(), userDTO.getUserType(),
                    userDTO.getPhoneNr(), userDTO.getEmail());
            userService.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username already exists!");
        }
    }


    @PostMapping("/interrogate_sold")
    public ResponseEntity interrogateSold(@RequestBody UserDTO userDTO) {

        User userFromDb = userService.findUserByUserName(userDTO.getUserName());
        Accounts accountsFromDb = accountsService.findAccountsByOwner(userFromDb);
        if (userFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user does not exist!");
        }
        if (accountsFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No account found");
        }
        SoldDTO responseDTO = modelMapper.map(accountsFromDb, SoldDTO.class);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO.toString());

    }

    @PostMapping("/get_all_users")
    public ResponseEntity show_all_users(@RequestBody UserDTO userDTO) {

        User userFromDb = userService.findUserByUserName(userDTO.getUserName());

        if (userFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user does not exist!");
        }
        if (userFromDb.getUserType().equals("ADMIN")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userService.findAll());

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("If you are not admin you cannot enter");
        }


    }
}

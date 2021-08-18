package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.NewAccountDTO;
import com.atm_machine_web.controller.dto.SoldDTO;
import com.atm_machine_web.controller.dto.UserDTO;
import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;
import com.atm_machine_web.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO.toString());

    }
//de modificat name -ingul


    @GetMapping("/all/{userName}")
    public ResponseEntity getAllUsers(@PathVariable("userName") String userName) {

        User userFromDb = userService.findUserByUserName(userName);

        if (userFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user does not exist!");
        }
        if (userFromDb.getUserType().equals("ADMIN")) {

            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("If you are not admin you cannot enter");
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


    @PostMapping("/add_account")
    public ResponseEntity addAccount(@RequestBody NewAccountDTO dto) {
        User owner = userService.findUserByUserName(dto.getUserName());
        Accounts accountsFromDb = accountsService.findAccountsByOwnerAndCurrencyType(owner,dto.getCurrencyType());
        if (accountsFromDb == null) {
            Accounts newAccount = new Accounts(owner, dto.getSold(), dto.getCurrencyType());
            accountsService.save(newAccount);
            NewAccountDTO responseDTO = modelMapper.map(newAccount, NewAccountDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This account already exists!");
        }
    }



}

package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.AtmDTO;
import com.atm_machine_web.controller.dto.WithdrawDTO;
import com.atm_machine_web.controller.dto.WithdrawResponseDTO;
import com.atm_machine_web.entity.Atm;
import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Transactions;
import com.atm_machine_web.model.User;
import com.atm_machine_web.notification.Notification;
import com.atm_machine_web.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

public class AtmController {
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


    @PostMapping("/refill_stacks_atm")
    public ResponseEntity refillStacksAtm(@RequestParam Integer nrNotes) {

        List<Notes> notes = notesService.findAll();

        Atm atmFromDb = atmService.findAtmByAtmId(1L);

        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {

            atmService.updateStacks(atmFromDb, atmFromDb.getStacks(), notes, nrNotes);
            atmService.save(atmFromDb);
            AtmDTO atmDto = modelMapper.map(atmFromDb, AtmDTO.class);

            return ResponseEntity.status(HttpStatus.OK).body(atmDto);
        }

    }


    @PostMapping("/withdraw_from_atm")
    public ResponseEntity withdraw(@RequestBody WithdrawDTO dto) {

        User owner = userService.findUserByUserName(dto.getUsername());
        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        Atm atmFromDb = atmService.findAtmByAtmId(1L);
        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {
            if (accountsFromDb == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
            } else {
                Float sold = accountsFromDb.getSold();
                atmService.countWithdraw(atmFromDb, atmFromDb.getStacks(), sold, dto.getSum());
                Float newSold = accountsFromDb.withdrawFromSold(dto.getSum());
                accountsFromDb.setSold(newSold);

                WithdrawResponseDTO responseDTO = modelMapper.map(atmFromDb, WithdrawResponseDTO.class);
                responseDTO.setUsername(dto.getUsername());
                responseDTO.setCurrency(dto.getCurrency());
                responseDTO.setSold(newSold);

                Notification not = atmService.showIfAlert(dto.getSum(), atmFromDb.getStacks(), atmFromDb.getAtmMoney());
                responseDTO.setMessage(not.getNotificationMessage());

                Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now(), accountsFromDb.getSold());
                transactionsService.save(newTransaction);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);


            }

        }
    }

    @GetMapping("/show_balance_atm")
    public ResponseEntity showAtmBalance() {

        Atm atmFromDb = atmService.findAtmByAtmId(1L);
        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(atmFromDb.getStacks().toString());
        }

    }

    @GetMapping("/show_stacks/")

    public String showStacks(@RequestBody Notes newNote) {
        return stacksService.findAllByNote(newNote).toString();
    }

    @GetMapping("/show_notes")
    public String showNotes() {
        return notesService.findAll().toString();
    }


}

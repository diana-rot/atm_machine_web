package com.atm_machine_web.controller;

import com.atm_machine_web.controller.dto.*;
import com.atm_machine_web.model.*;
import com.atm_machine_web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.atm_machine_web.entity.Atm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

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

            atmService.updateStacks(notes, nrNotes);
            atmService.save(atm);
            AtmDTO atmDto = modelMapper.map(atmFromDb, AtmDTO.class);

            return ResponseEntity.status(HttpStatus.OK).body(atmDto);
        }

    }


    @PostMapping("/add_note_to_atm")
    public ResponseEntity addNoteToAtm(@RequestBody NoteToAtmDTO dto) {

        User owner = userService.findUserByUserName(dto.getUsername());
        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        Atm atmFromDb = atmService.findAtmByAtmId(1L);

        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {
            if (accountsFromDb == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
            } else {

                Notes note = notesService.findNotesByType(dto.getNoteType());
                atmService.refillStackNote(note, dto.getNrNotes());


                //set sold in db function
                accountsService.updateSold(note.getValue() , dto.getNrNotes(),accountsFromDb);
                //nu se updateaza atmmoney
                System.out.println(accountsFromDb.getSold());

                Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now(), accountsFromDb.getSold());
                transactionsService.save(newTransaction);

                AtmDTO atmDto = modelMapper.map(atmFromDb, AtmDTO.class);
                return ResponseEntity.status(HttpStatus.OK).body(atmDto);
            }
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
                if (sold > dto.getSum()) {

                    List<Integer> extractedNotes = atmService.countWithdraw(dto.getSum());
                    accountsFromDb.withdrawFromSold(dto.getSum());
                    Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now(), accountsFromDb.getSold());
                    transactionsService.save(newTransaction);
                    WithdrawResponseDTO responseDTO = modelMapper.map(atmFromDb, WithdrawResponseDTO.class);
                    responseDTO.setUsername(dto.getUsername());
                    responseDTO.setCurrency(dto.getCurrency());
                    responseDTO.setSold(accountsFromDb.getSold());
                    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds! Check your sold");
                }

            }

        }
    }

    @GetMapping("/show_stacks")
    public String showStacks(@RequestBody Notes newNote) {
        return stacksService.findAllByNote(newNote).toString();
    }

    @GetMapping("/show_notes")
    public String showNotes() {
        return notesService.findAll().toString();
    }


}

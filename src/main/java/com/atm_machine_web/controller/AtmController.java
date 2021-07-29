package com.atm_machine_web.controller;

import com.atm_machine_web.model.*;
import com.atm_machine_web.service.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import com.atm_machine_web.entity.Atm;
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

    //aici trebuie si ownerul sa vad daca e admin sau nu
    @PostMapping("/refill_stacks_atm")
    public ResponseEntity refillStacksAtm(@RequestParam Integer nrNotes) {

        List<Notes> notes = notesService.findAll();

        Atm atmFromDb = atmService.findAtmByAtmId(1L);

        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {

            StringBuilder testString = new StringBuilder();
            testString.append(atmFromDb.updateStacks(notes, nrNotes));
            testString.append(atmFromDb.messageAfterUpdateStacks(notes, nrNotes));
            testString.append("Acum in bancomat sunt:" + atmFromDb.getAtmMoney() + "\n");
            //atmFromDb.setAtmMoney(atm.getAtmMoney());
            atmService.save(atm); //ar trebui aici un update?

            return ResponseEntity.status(HttpStatus.OK).body(testString);
        }

    }


    @PostMapping("/add_note_to_atm")
    public ResponseEntity addNoteToAtm(@RequestBody User owner,
                                       @RequestParam String noteType,
                                       @RequestParam Integer nrNotes) {


        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        Atm atmFromDb = atmService.findAtmByAtmId(1L);

        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {
            if (accountsFromDb == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
            } else {

                Notes note = notesService.findValueByType(noteType); //se va sterge
                StringBuilder testString = new StringBuilder("test de la refill din atm: "); // param in functie
                testString.append(atmFromDb.refillStackNote(note, nrNotes));

                Float sold = accountsFromDb.getSold();
                sold = sold + note.getValue() * nrNotes;
                accountsFromDb.setSold(sold);

                testString.append("sold is " + sold);
                Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now(), accountsFromDb.getSold());
                transactionsService.save(newTransaction);
                return ResponseEntity.status(HttpStatus.OK).body(testString);
            }
        }

    }


    @PostMapping("/withdraw_from_atm")
    public ResponseEntity withdraw(Integer sum, @RequestBody User owner) {
        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        Atm atmFromDb = atmService.findAtmByAtmId(1L);

        if (atmFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This atm  doesn't exist");
        } else {
            if (accountsFromDb == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
            } else {
                Float sold = accountsFromDb.getSold();
                if (sold > sum) {
                    StringBuilder returnMessage = new StringBuilder();
                    //mai pot pune aici mesaje de atentionare user
                    List<Integer> extractedNotes = atmFromDb.countWithdraw(sum);
                    returnMessage.append(atmFromDb.messageAfterWithdraw(extractedNotes, sum));
                    accountsFromDb.withdrawFromSold(sum);
                    Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now(), accountsFromDb.getSold());
                    transactionsService.save(newTransaction);
                    return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
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

    @PostMapping("/add_account")
    public ResponseEntity addAccount(@RequestBody Accounts newAccount) {
        Accounts accountsFromDb = accountsService.findAccountsByOwner(newAccount.getOwner());
        if (accountsFromDb == null) {
            Accounts addedAccount = accountsService.save(newAccount);
            return ResponseEntity.status(HttpStatus.OK).body(addedAccount);
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

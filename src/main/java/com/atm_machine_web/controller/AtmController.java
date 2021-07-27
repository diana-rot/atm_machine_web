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
import java.util.ArrayList;
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


    @PostMapping("/refill_notes")
    public ResponseEntity refillNotes(@RequestBody User owner) {
        // @RequestBody List<Stacks> stacksNotes
        List<Stacks> stacksNotes = new ArrayList<>();
        List<Notes> notes = notesService.findAll();
        for (Notes note : notes) {
            stacksNotes.add(new Stacks(note, 5));
        }

        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        if (accountsFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
        } else {
            Transactions oldTransaction = transactionsService.findTransactionsByAccountId(accountsFromDb);
            Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now());
            StringBuilder testString = new StringBuilder("test de la refill din atm: ");

            if (oldTransaction == null) {
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("du tr drwas!");
                testString.append("am intrat pe batran egal cu nul");
                testString.append(newTransaction.updateStacks(stacksNotes));

            } else {
                newTransaction.setStacks(oldTransaction.getStacks());
                testString.append(newTransaction.updateStacks(stacksNotes));
                List<Stacks> stacks = newTransaction.getStacks();
                testString.append("sunt in else" + "control" + stacks.toString());
            }
//

            transactionsService.save(newTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(testString);
        }


    }

    @PostMapping("/add_note")
    public ResponseEntity addNote(@RequestBody User owner,
                                  @RequestParam Integer nrNotes) {


        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);

        if (accountsFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
        } else {
            Transactions oldTransaction = transactionsService.findTransactionsByAccountId(accountsFromDb);
            Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now());
            Notes note = notesService.findValueByType("Leu_100"); //se va sterge
            StringBuilder testString = new StringBuilder("test de la refill din atm: ");

            if (oldTransaction == null) {
                testString.append(newTransaction.refillStackNote(note, nrNotes));
            } else {
                newTransaction.setStacks(oldTransaction.getStacks());
                testString.append(newTransaction.refillStackNote(note, nrNotes));
                List<Stacks> stacks = newTransaction.getStacks();
                testString.append("sunt in else" + "control" + stacks.toString());
            }

            Float sold = accountsFromDb.getSold();
            sold = sold + note.getValue() * nrNotes;
            accountsFromDb.setSold(sold);
            testString.append("sold is " + sold);
            transactionsService.save(newTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(testString);
        }


    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(Integer sum, @RequestBody User owner) {
        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
        if (accountsFromDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
        } else {
            Float sold = accountsFromDb.getSold();
            if (sold > sum) {
                Transactions oldTransaction = transactionsService.findTransactionsByAccountId(accountsFromDb);

                Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now());
                newTransaction.setStacks(oldTransaction.getStacks());
                List<Stacks> stacks = newTransaction.getStacks();


//               List<Transactions>  tr = transactionsService.findTransactionsByAccountIdOrderByTransactionId(accountsFromDb);

                StringBuilder message = newTransaction.countWithdraw(sum);
                sold = sold - sum;
                accountsFromDb.setSold(sold);
                transactionsService.save(newTransaction);
                return ResponseEntity.status(HttpStatus.OK).body(stacks);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds! Check your sold");
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


    @GetMapping
    public String welcome() {
        return ("Hello! Select an action: /withdraw , /showNoteType, /balance");
    }
}


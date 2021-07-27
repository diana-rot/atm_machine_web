package com.atm_machine_web.controller;
import com.atm_machine_web.model.*;
import com.atm_machine_web.service.*;
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

    @PostMapping("/refill_notes")
    public ResponseEntity refillNotes(@RequestBody User owner,
                                     @RequestParam Integer count) {


        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);

        if(accountsFromDb == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
        }else {
            Float sold = accountsFromDb.getSold();
           // Transactions oldTr = transactionsService.findTransactionsByAccountId(accountsFromDb);

            Transactions newTransaction = new Transactions(accountsFromDb, LocalDate.now());
            List<Notes> notes=  notesService.findAll();
            StringBuilder newString = new StringBuilder();
            newString.append(newTransaction.refillSumForNotes(notes, count));

//            accountsFromDb.setSold(sold);
//            newString.append("sold is " + sold);
            transactionsService.save(newTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(newString);
        }


    }
    @PostMapping("/refill_note")
    public ResponseEntity refillNote(@RequestBody User owner,
                                         @RequestParam Integer sum) {


        Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);

        if(accountsFromDb == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
        }else {
            Float sold = accountsFromDb.getSold();
            Transactions newTransaction = new Transactions( accountsFromDb, LocalDate.now());
            Notes note = notesService.findValueByType("Leu_100");
            StringBuilder newString = new StringBuilder();
            newString.append(newTransaction.refillSumForNote(note, sum));
            sold = sold + sum;
            accountsFromDb.setSold(sold);
            newString.append("sold is " + sold);
            transactionsService.save(newTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(newString);
        }


    }

@PostMapping("/withdraw")
    public ResponseEntity withdraw(Integer sum, @RequestBody User owner) {
       Accounts accountsFromDb = accountsService.findAccountsByOwner(owner);
       if(accountsFromDb == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user doesn't have any account!");
       }else{
           Float sold = accountsFromDb.getSold();
           if(sold > sum){
               Transactions oldTransaction = transactionsService.findTransactionsByAccountId(accountsFromDb);

               Transactions newTransaction = new Transactions(accountsFromDb,LocalDate.now());
               newTransaction.setStacks(oldTransaction.getStacks());
               List<Stacks> stacks =newTransaction.getStacks();


//               List<Transactions>  tr = transactionsService.findTransactionsByAccountIdOrderByTransactionId(accountsFromDb);

               StringBuilder message =  newTransaction.countWithdraw(sum);
               sold = sold - sum;
               accountsFromDb.setSold(sold);
               transactionsService.save(newTransaction);
               return ResponseEntity.status(HttpStatus.OK).body(stacks);
           }else{
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
        if( accountsFromDb == null){
            Accounts addedAccount = accountsService.save(newAccount);
            return ResponseEntity.status(HttpStatus.OK).body(addedAccount);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This account already exists!");
        }
    }

    @PostMapping("/add_user")
    public ResponseEntity addUser(@RequestBody User newUser) {
        User userFromDb = userService.findUserByUserName(newUser.getUserName());
        if(userFromDb == null){
            User addedUser = userService.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(addedUser);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username already exists!");
        }
    }


    @GetMapping
    public String welcome() {
        return ("Hello! Select an action: /withdraw , /showNoteType, /balance");
    }

    @GetMapping("/notes")
    public String showNoteType() {
        return atm.getNotes().toString();
    }

    @GetMapping("/balance")
    public StringBuilder ShowBalance() {
        StringBuilder availableNotes = new StringBuilder();
        availableNotes.append("Our Atm now currently has:");
        availableNotes.append(System.getProperty("line.separator"));
        availableNotes.append(atm.getNotes().toString()).append(" lei");
        availableNotes.append(System.getProperty("line.separator"));
        availableNotes.append(atm.getNoteFrequency().toString()).append("with the frequencies");
        return availableNotes;
    }


}



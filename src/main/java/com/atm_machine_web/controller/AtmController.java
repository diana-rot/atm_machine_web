package com.atm_machine_web.controller;

import com.atm_machine_web.entity.Atm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AtmController {
    @Autowired
    Atm atm;

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

    @PostMapping("/refill")
    public ResponseEntity<String> refill(@RequestBody List<Integer> noteFrequency,
                                         @RequestParam boolean isAdmin) {
        if (!isAdmin) {
            return new ResponseEntity<>("Unauthorized admin", HttpStatus.UNAUTHORIZED);
        }
        atm.addMoney(noteFrequency);
        return new ResponseEntity<>(atm.getNoteFrequency().toString(), HttpStatus.OK);

    }

    @PostMapping("/withdraw")
    public StringBuilder withdraw(@RequestParam int sum) {
        try {
            return atm.countCurrency(sum, 5, atm.getNotes(), atm.getNoteFrequency());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}



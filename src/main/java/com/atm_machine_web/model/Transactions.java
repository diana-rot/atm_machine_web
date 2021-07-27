package com.atm_machine_web.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long transactionId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accountId;
    @Column(name = "date")
    LocalDate date;
    @OneToMany(cascade = {CascadeType.ALL})
    List<Stacks> stacks;
    boolean stackInitialized;

    public Transactions(Long transactionId, Accounts accountId, LocalDate date) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.date = date;
        stackInitialized = false;

    }

    public Transactions() {

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }

    public void initializeStacks() {
        stacks = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", date=" + date +
                ", stacks=" + stacks +
                '}';
    }

    public StringBuilder printCurrency(Integer sum) {
        StringBuilder message = new StringBuilder();
        message.append("Notes Count : ");
        for (Stacks stack : stacks) {
            if (stack.getCount() != 0) {
                message.append(stacks.toString());
                message.append(System.getProperty("line.separator"));
            }
        }

        return message;
    }

    public StringBuilder countWithdraw(Integer sum, List<Stacks> stacks) {
        Integer restValueNote;
        if (stackInitialized == false) {
            this.stacks = new ArrayList<>();
            stackInitialized = true;
            StringBuilder newString = new StringBuilder("No money here to withdraw");
            return newString;
        }

        for (Stacks stack : stacks) {
            Integer noteHolder = stack.getNote().getValue();
            if (sum >= noteHolder) {
                restValueNote = sum / noteHolder;
                if (stack.getCount() - restValueNote > 0) {

                    stack.setCount(sum / stack.getNote().getValue());
                    sum = sum - stack.getCount() * stack.getNote().getValue();


                } else if (stack.getCount() > 0) {
                    while (stack.getCount() != 0) {
                        sum = sum - stack.getNote().getValue();
                        stack.decreaseCount(1);

                    }
                }
            }
        }
        return printCurrency(sum);
    }


    public void initStacks(List<Stacks> stacks, Notes note, Long id_note, Integer count) {
        if (stackInitialized == false) {
           stacks = new ArrayList<>();
            stackInitialized = true;
            this.stacks.add(new Stacks(count, note));


        } else {
            for (Stacks stack : stacks) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(count);


                } else {
                    stacks.add(new Stacks( count, note));
                    id_note += 1;

                }


            }
        }
    }

    public StringBuilder refillSumForNotes(List<Notes> notes, Integer count) {

        StringBuilder newString = new StringBuilder("is ok");
        Integer totalSum = 0;
        for (Notes note : notes) {
            initStacks(this.stacks, note,note.getNoteId(), count);
            totalSum += count * note.getValue();
            newString.append(totalSum);
        }
        return newString;
    }


    public StringBuilder refillSumForNote(Notes note, Integer sum) {
        Long id_note = 1L;
        StringBuilder newString = new StringBuilder("is ok");
        if (stackInitialized == false) {
            stacks = new ArrayList<>();
            stackInitialized = true;
            stacks.add(new Stacks( sum, note));
            id_note += 1;
            newString.append(note.toString() + " " + sum);
            return newString;
        }

        for (Stacks stack : stacks) {
            if (stack.getNote().getType().equals(note.getType())) {
                stack.increaseCount(sum);
                newString.append("already here" + note.toString() + " " + sum);

            } else {
                stacks.add(new Stacks( sum, note));
                id_note += 1;
                newString.append(stack.getNote().toString() + " " + sum);
            }
        }
        return newString;


    }
}






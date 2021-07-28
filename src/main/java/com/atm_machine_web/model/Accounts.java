package com.atm_machine_web.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "sold", updatable = true)
    Float sold;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;


    @OneToMany(cascade = {CascadeType.ALL})
    List<Stacks> stacks;


    public Accounts() {
    }

    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }


    public Accounts(User owner, Float sold, String currencyType) {
        this.owner = owner;
        this.sold = sold;
        this.currencyType = currencyType;
        this.stacks = new ArrayList<>();
    }


    public StringBuilder refillStackNote(Notes note, Integer nrNotes) {

        StringBuilder messageReturn = new StringBuilder("refillStackNote");
        if (stacks.isEmpty()) {
            stacks.add(new Stacks(note, nrNotes));
            messageReturn.append("Am inserat pe primul if o data tb sa apara si atat!!" + note.toString() + " " + nrNotes);

        } else {
            messageReturn.append("de ce nu ma vrei");
            for (Stacks stack : stacks) {
                if (stack.getNote().getType().equals(note.getType())) {
                    // messageReturn("")
                    stack.increaseCount(nrNotes);
                    messageReturn.append("already here" + note.toString() + " " + nrNotes);
                    messageReturn.append("HAI SA AFLAM " + stack.toString() + "count" + stack.getCount());
                }

//                    stacks.add(new Stacks(note, nrNotes ));
//                    messageReturn.append("aici e rez"+stack.getNote().toString() + " " + nrNotes);

            }
        }
        return messageReturn;


    }

    //public void init
    public StringBuilder updateStacks(List<Notes> availableNotes, Integer nrNotes) {
        //List<Integer> counts,
        StringBuilder testString = new StringBuilder("refilledStacks");
        Integer sumAdded = 0;
        Integer valueCurrentNote = 0;
        boolean containsStack = false;
        //aici tb sa fac sa dau lista direct

        if (stacks.isEmpty()) {
            stacks.add(new Stacks(availableNotes.get(0), nrNotes));//100
            stacks.add(new Stacks(availableNotes.get(1), nrNotes));//50
            stacks.add(new Stacks(availableNotes.get(2), nrNotes));//10
            stacks.add(new Stacks(availableNotes.get(3), nrNotes));//5
            stacks.add(new Stacks(availableNotes.get(4), nrNotes));//1
            testString.append("SE ADAUGA " + availableNotes.get(0).toString() + "de n ori" + nrNotes);
            testString.append("SE ADAUGA " + availableNotes.get(1).toString() + "de n ori" + nrNotes);
            testString.append("SE ADAUGA " + availableNotes.get(2).toString() + "de n ori" + nrNotes);
            testString.append("SE ADAUGA " + availableNotes.get(3).toString() + "de n ori" + nrNotes);

        } else {
            testString.append(stacks.toString());
            for (Notes iteratorNote : availableNotes) {
                for (Stacks stack : stacks) {
                    if (stack.getNote().getType().equals(iteratorNote.getType())) {
                        stack.increaseCount(nrNotes);

                    }


                    testString.append("\n");
                }
            }
        }
        return testString;

    }

    //AICI TB NEAPARAT REFACUTA!!!!
    public StringBuilder countWithdraw(Integer sum) {
        Integer restValueNote;
        StringBuilder returnMessage = new StringBuilder("extrag : ");

        for (Stacks stack : stacks) {
            Integer noteHolder = stack.getNote().getValue();
            returnMessage.append("am intrat in for" + noteHolder.toString());

            if (sum >= noteHolder) {
                returnMessage.append("+ suma e suficient de mare ca bancnota");
                restValueNote = sum / noteHolder;
                returnMessage.append("restValue note" + sum);
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
        return returnMessage;

    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Float getSold() {
        return sold;
    }

    public void setSold(Float sold) {
        this.sold = sold;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }


}

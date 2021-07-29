package com.atm_machine_web.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "Accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "sold")
    Float sold;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;


    @OneToMany(cascade = {CascadeType.ALL})
    List<Stacks> stacks;


    public Accounts() {
    }

    public Accounts(User owner, Float sold, String currencyType) {
        this.owner = owner;
        this.sold = sold;
        this.currencyType = currencyType;
        this.stacks = new ArrayList<>();
    }

    public Float withdrawFromSold(Integer sum){
        Float newSold = getSold() - sum;
        setSold(newSold);
        return newSold;
    }



    public StringBuilder messageAfterWithdraw(List<Integer> noteWithdrawer, Integer sumToBeExtracted) {
        StringBuilder returnMessage = new StringBuilder("din suma" + sumToBeExtracted);
        returnMessage.append("au fost extrase bancnotele : " + "\n");
        returnMessage.append("You have extracted" + "\n" + noteWithdrawer.toString());
        return returnMessage;
    }

    public List<Integer> countWithdraw(Integer sumToBeExtracted) {

        List<Integer> noteWithdrawCounter = new ArrayList();
        noteWithdrawCounter.addAll(Arrays.asList(0, 0, 0, 0, 0, 0));

        for (Stacks stack : stacks) {
            Integer currentNoteValue = stack.getNote().getValue();

            if (isCounterNotesGreaterOrEqualThanRest(sumToBeExtracted, currentNoteValue)) {
                Integer indexOfCurrentExtractedNote = stacks.indexOf(stack);
                Integer restValueNote = sumToBeExtracted / currentNoteValue;

                if (isCounterNotesGreaterThanRest(stack.getCount(), restValueNote)) {

                    noteWithdrawCounter.set(indexOfCurrentExtractedNote, restValueNote);
                    sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
                            noteWithdrawCounter.get(indexOfCurrentExtractedNote) * currentNoteValue);
                    stack.decreaseCount(noteWithdrawCounter.get(indexOfCurrentExtractedNote));

                } else if (isCounterNotesGreaterThanRest(stack.getCount(), 0)) {
                    while (stack.getCount() != 0) {
                        sumToBeExtracted = updateSumToBeextracted(sumToBeExtracted,
                                currentNoteValue);
                        ;
                        stack.decreaseCount(1);
                        Integer element = noteWithdrawCounter.get(indexOfCurrentExtractedNote);
                        noteWithdrawCounter.set(indexOfCurrentExtractedNote, element + 1);
                    }
                }
            }

        }

        return noteWithdrawCounter;

    }


    public Boolean isCounterNotesGreaterThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote > restValueNote) {
            return true;
        } else return false;
    }

    public Integer updateSumToBeextracted(Integer sumToBeExtracted, Integer noteValue) {
        sumToBeExtracted -= noteValue;
        return sumToBeExtracted;

    }

    public Boolean isCounterNotesGreaterOrEqualThanRest(Integer counterNote, Integer restValueNote) {
        if (counterNote >= restValueNote) {
            return true;
        } else return false;
    }


    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
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

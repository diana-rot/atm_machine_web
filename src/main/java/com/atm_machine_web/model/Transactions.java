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

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    List<Stacks> stacks;
    boolean stackInitialized;

    public Transactions(Accounts accountId, LocalDate date) {
        this.accountId = accountId;
        this.date = date;
       // stackInitialized = false;

    }

    public Transactions() {

    }

    //AICI TB NEAPARAT REFACUTA!!!!
    public StringBuilder countWithdraw(Integer sum) {
        Integer restValueNote;
        StringBuilder returnMessage = new StringBuilder("extrag : ");
        if (stackInitialized == false) {
            this.stacks = new ArrayList<>();
            stackInitialized = true;
            returnMessage.append("No money in stacks");
        } else {
            for (Stacks stack : stacks) {
                Integer noteHolder = stack.getNote().getValue();
                returnMessage.append("am intrat in for" + noteHolder.toString());

                if (sum >= noteHolder) {
                    returnMessage.append("+ suma e suficient de mare ca bancnota");
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
        }
        return returnMessage;

    }

    //generala cu tratat toate cazurile + dar poate fi utilizata si pentru urmatoarea
    public void addNotesStacks( Notes note, Integer nrNotes) {

        if (stackInitialized == false) {
            stacks = new ArrayList<>();
            stackInitialized = true;
            stacks.add(new Stacks(note, nrNotes));


        } else {
            for (Stacks stack : stacks) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(nrNotes);

                } else {
                    stacks.add(new Stacks(note, nrNotes));
                }
            }
        }
    }
    //to be done

    public StringBuilder updateStacks(List<Stacks> refilledStacks) {

        StringBuilder testString = new StringBuilder("refilledStacks");
        Integer sumAdded = 0;
        Integer nrNotes = 0;
        Integer valueCurrentNote = 0;
        Notes updateNote;

        if (stackInitialized == false) {
            stacks = new ArrayList<>();
            stackInitialized = true;
            Integer nrNote = refilledStacks.get(0).getCount();
            stacks.add(new Stacks(refilledStacks.get(0).getNote(),nrNote));
        }else {
            testString.append(stacks.toString());
            for (Stacks otherStack : refilledStacks) {

                nrNotes = otherStack.getCount();
                valueCurrentNote = otherStack.getNote().getValue();
                updateNote = otherStack.getNote();

//            //addNotesStacks(this.stacks,otherStack.getNote(), nrNotes);
            refillStackNote(updateNote,nrNotes);


                sumAdded += nrNotes * valueCurrentNote;
                testString.append(sumAdded + "this should be the return value");
                testString.append("a new note" + otherStack.toString());
                testString.append("\n");
            }
        }
        return testString;

    }


    public StringBuilder refillStackNote(Notes note, Integer nrNotes) {

        StringBuilder messageReturn = new StringBuilder("refillStackNote");
        if (stackInitialized == false) {
            stacks = new ArrayList<>();
            stackInitialized = true;
            stacks.add(new Stacks(note, nrNotes));
            messageReturn.append("Am inserat pe primul if" + note.toString() + " " + nrNotes);

        } else {
            for (Stacks stack : stacks) {
                if (stack.getNote().getType().equals(note.getType())) {
                    stack.increaseCount(nrNotes);
                    messageReturn.append("already here" + note.toString() + " " + nrNotes);

                } else {
                    stacks.add(new Stacks(note, nrNotes ));
                    messageReturn.append(stack.getNote().toString() + " " + nrNotes);
                }
            }
        }
        return messageReturn;


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

}






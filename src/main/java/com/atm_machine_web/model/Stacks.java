package com.atm_machine_web.model;

import javax.persistence.*;

@Entity(name = "Stacks")
public class Stacks {
    @Id
//    @SequenceGenerator(
//            name = "stacks_sequence",
//            sequenceName = "stacks_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "stacks_sequence")

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stackIdNote;
    @Column(name = "count")
    Integer count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note")
    Notes note;


    public Stacks( Integer count, Notes note) {
        //this.stackIdNote = stackIdNote;
        this.count = count;
        this.note = note;
    }

    public Stacks() {

    }

    public Long getStackIdNote() {
        return stackIdNote;
    }

    public void setStackIdNote(Long stackIdNote) {
        this.stackIdNote = stackIdNote;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Notes getNote() {
        return note;
    }

    public void setNote(Notes note) {
        this.note = note;
    }

    public Integer increaseCount(Integer newCount) {
        return count + newCount;
    }

    public Integer decreaseCount(Integer newCount) {
        return count - newCount;
    }

    @Override
    public String toString() {
        return "Stacks{" +
                "stackIdNote=" + stackIdNote +
                ", count=" + count +
                ", note=" + note +
                '}';
    }
}

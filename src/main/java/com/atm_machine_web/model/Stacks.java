package com.atm_machine_web.model;

import javax.persistence.*;


@Entity(name = "Stacks")
public class Stacks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stack_id", nullable = false)
    private Long stackIdNote;
    @Column(name = "count")
    Integer count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note")
    Notes note;


    public Stacks(Notes note, Integer count) {

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

    public void increaseCount(Integer newCount) {
       this.count =   this.count + newCount;
    }

    public  void decreaseCount(Integer newCount) {
        this.count =   this.count - newCount;
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

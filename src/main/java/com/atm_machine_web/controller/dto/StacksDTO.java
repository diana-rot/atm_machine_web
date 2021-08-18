package com.atm_machine_web.controller.dto;

import com.atm_machine_web.model.Notes;


public class StacksDTO {
    Integer count;
    Notes note;

    @Override
    public String toString() {
        return "StacksDTO{" +
                "count=" + count +
                ", note=" + note.getValue() + " "+ note.getType()+
                '}';
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
}

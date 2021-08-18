package com.atm_machine_web.controller.dto;

public class NoteToAtmDTO {
        String username;
        String noteType;
        Integer nrNotes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public Integer getNrNotes() {
        return nrNotes;
    }

    public void setNrNotes(Integer nrNotes) {
        this.nrNotes = nrNotes;
    }
}

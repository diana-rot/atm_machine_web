package com.atm_machine_web.model;

public class User {
    StringBuilder name;
    StringBuilder phoneNr;
    StringBuilder email;

    public StringBuilder getName() {
        return name;
    }


    public StringBuilder getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(StringBuilder phoneNr) {
        this.phoneNr = new StringBuilder();
        this.phoneNr.append(phoneNr);
    }

    public StringBuilder getEmail() {
        return email;
    }

    public void setEmail(StringBuilder email) {
        this.email = new StringBuilder();
        this.email.append(email);
    }

    public void setName(StringBuilder name) {
        this.name = new StringBuilder();
        this.name.append(name);
    }
    public User(StringBuilder name, StringBuilder phoneNr, StringBuilder email) {
        this.name = name;
        this.phoneNr = phoneNr;
        this.email = email;
    }
}

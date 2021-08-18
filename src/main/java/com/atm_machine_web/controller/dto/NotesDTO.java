package com.atm_machine_web.controller.dto;

import javax.persistence.Column;

public class NotesDTO {

    String type;
    Integer value;
    String currency;

    @Override
    public String toString() {
        return "NotesDTO{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

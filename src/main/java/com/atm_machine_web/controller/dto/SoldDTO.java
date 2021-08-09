package com.atm_machine_web.controller.dto;

import com.atm_machine_web.model.User;

public class SoldDTO {
    User owner;
    Float sold;
    String currencyType;

    @Override
    public String toString() {
        return "Hello!" + owner.getUserName() + "Your current sold is:" +
                "SoldDTO{" +
                ", sold=" + sold +
                ", currencyType='" + currencyType + '\'' +
                '}';
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

package com.atm_machine_web.controller.dto;

import com.atm_machine_web.model.Stacks;

import java.util.List;

public class WithdrawResponseDTO {
    String username;
    String currency;
    List<Stacks> stacks;
    Float atmMoney;
    Float sold;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getSold() {
        return sold;
    }

    public void setSold(Float sold) {
        this.sold = sold;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }

    public Float getAtmMoney() {
        return atmMoney;
    }

    public void setAtmMoney(Float atmMoney) {
        this.atmMoney = atmMoney;
    }

    @Override
    public String toString() {
        return "WithdrawResponseDTO{" +
                username +  + '\'' +
                "You have recieved message='" + message + '\'' +
                ", currency='" + currency + '\'' +
                ", stacks=" + stacks +
                ",Money left in atm=" + atmMoney +
                ", Your current sold=" + sold +
                '}';
    }
}

package com.atm_machine_web.controller.dto;

import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.model.User;

import javax.persistence.*;
import java.util.List;

public class AccountsDTO {
     Long accountId;
     User owner;
     Float sold;
     String currencyType;
    List<Stacks> stacks;

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

    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }
}

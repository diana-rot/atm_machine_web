package com.atm_machine_web.controller.dto;

import com.atm_machine_web.model.Stacks;

import javax.persistence.CascadeType;
import java.util.List;

public class AtmDTO {
    private Long atmId;
    List<Stacks> stacks;
    Float atmMoney;

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
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
}

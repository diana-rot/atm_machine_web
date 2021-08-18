package com.atm_machine_web.entity;

import com.atm_machine_web.model.Stacks;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "Atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stacks")
    List<Stacks> stacks;

    @Column(name = "atm_money", updatable = true)
    Float atmMoney;


    public Atm(Float atmMoney) {

        this.stacks = new ArrayList<Stacks>();
        this.atmMoney = atmMoney;
    }

    public Atm(Long atmId, Float atmMoney) {

        this.stacks = new ArrayList<Stacks>();
        this.atmId = atmId;
        this.atmMoney = atmMoney;
    }

    public Atm() {

    }

    public Float getAtmMoney() {
        return atmMoney;
    }

    public void setAtmMoney(Float atmMoney) {
        this.atmMoney = atmMoney;
    }

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

    @Override
    public String toString() {
        return "Atm{" +
                "atmId=" + atmId +
                ", stacks=" + stacks +
                ", atmMoney=" + atmMoney +
                '}';
    }



}

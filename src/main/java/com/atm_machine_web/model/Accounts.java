package com.atm_machine_web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Accounts")
public class Accounts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "sold")
    Float sold;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;


    @OneToMany(cascade = {CascadeType.ALL})
    List<Stacks> stacks;


    public Accounts() {
    }

    public Accounts(User owner, Float sold, String currencyType) {
        this.owner = owner;
        this.sold = sold;
        this.currencyType = currencyType;
        this.stacks = new ArrayList<>();
    }

    public Float withdrawFromSold(Integer sum){
        Float newSold = getSold() - sum;
        setSold(newSold);
        return newSold;
    }



    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }


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


}

package com.atm_machine_web.model;

import javax.persistence.*;
import java.util.List;
@Entity(name = "Accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "sold", updatable = true)
    Float sold;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;

    public Accounts() {
    }

    public Accounts(User owner, Float sold, String currencyType) {
        this.owner = owner;
        this.sold = sold;
        this.currencyType = currencyType;
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

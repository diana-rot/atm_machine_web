package com.atm_machine_web.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Accounts accountId;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "sold")
    Float sold;


    public Transactions( Accounts accountId, LocalDate date, Float sold) {

        this.accountId = accountId;
        this.date = date;
        this.sold = sold;
    }

    public Transactions() {

    }

    public Float getSold() {
        return sold;
    }

    public void setSold(Float sold) {
        this.sold = sold;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", date=" + date +
                '}';
    }

}






package com.atm_machine_web.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Money {
   
    Currency MoneyCurrency;
    LinkedList<Bill> bills;


    public enum Currency {
        LEI,
        EUR,
        DOLLAR;
    }

    public Currency getMoneyCurrency() {
        return MoneyCurrency;
    }

    public void setMoneyCurrency(Currency moneyCurrency) {
        MoneyCurrency = moneyCurrency;
    }

    public LinkedList<Bill> getBills() {
        return bills;
    }

    public void setBills(LinkedList<Bill> bills) {
        this.bills = bills;
    }

    public Money(Currency moneyCurrency, ArrayList<Bill> bills) {
        MoneyCurrency = moneyCurrency;
        switch (moneyCurrency) {
            case LEI:
                bills = new ArrayList<Bill>();//den enum value
               // bills.add(new Bill(LEU_100, 100 ));
                bills.add(new Bill(50,50));
                bills.add(new Bill(10,100));
                bills.add(new Bill(5,100));
                bills.add(new Bill(1,100));
                break;
            case EUR:
                break;
            case DOLLAR:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + moneyCurrency);

        }
    }
}


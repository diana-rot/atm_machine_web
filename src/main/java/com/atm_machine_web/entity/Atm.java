package com.atm_machine_web.entity;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;

import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

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


//    public void showIfAllert(Integer sum) {
//
//
//
//        if (noteFrequency.get(0) <= PercentageOfSum(100, 10)) {
//            (new SmsNotification(Notification.TypeNotification.Critical,
//                    new StringBuilder("100 LEI bills under 10% of max"),new StringBuilder("345"))).printNotification();
//        } else if( noteFrequency.get(0) < PercentageOfSum(100, 20)) {
//            (new EmailNotification(Notification.TypeNotification.Warning,
//                    new StringBuilder("100 LEI bills under 20% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//        }
//       if( noteFrequency.get(1) <= PercentageOfSum(50, 15)) {
//           (new EmailNotification(Notification.TypeNotification.Warning,
//                   new StringBuilder("50 lei bills under 15% of max"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//
//
//        }
//        if (RestTotalNotes(noteFrequency) == 0){
//            (new EmailNotification(Notification.TypeNotification.StockAllert,
//                    new StringBuilder("O stock of bills, please refill!"),new StringBuilder("help_center_bank@gmail.com"))).printNotification();
//        }
//        if(sum >= 200){
//            (new SmsNotification(Notification.TypeNotification.WithdrawOver200,
//                    new StringBuilder("You want to extract over 200 lei from ATM<>, if it s not you, URGENTLY CONTACT THE BANK"),
//                    new StringBuilder("0767893240"))).printNotification();
//        }


}

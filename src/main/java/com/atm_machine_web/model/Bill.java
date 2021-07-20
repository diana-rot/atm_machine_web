package com.atm_machine_web.model;

public class Bill {
    Integer value;
    Integer counter;
//    public enum LeuValues{
//        LEU_100,
//        LEU_50,
//        LEU_10,
//        LEU_5,
//        LEU_1;
//    }

    //LeuValues leu;
    public Bill(Integer leu,Integer value) {
        this.value = value;
        this.counter = leu;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "Bill{" +
                "value=" + value +
                ", counter=" + counter +
                '}';
    }


}



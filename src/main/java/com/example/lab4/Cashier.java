package com.example.lab4;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;



@RestController
public class Cashier {
    private Change changes;
    public int num1000;
    public int num500;
    public int num100;
    public int num20;
    public int num10;
    public int num5;

    public int numberChange;

    @RequestMapping(value = "/getChange/{change}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("change") int change){
        this.changes = new Change();
        numberChange = change;
        if(numberChange/1000 != 0){
            num1000 = numberChange/1000;
            numberChange = numberChange-(num1000*1000);
            this.changes.setB1000(num1000);
        }
        if(numberChange/500 != 0){
            num500 = numberChange/500;
            numberChange = numberChange-(num500*500);
            this.changes.setB500(num500);
        }
        if(numberChange/100 != 0){
            num100 = numberChange/100;
            numberChange = numberChange-(num100*100);
            this.changes.setB100(num100);
        }
        if(numberChange/20 != 0){
            num20 = numberChange/20;
            numberChange = numberChange-(num20*20);
            this.changes.setB20(num20);
        }
        if(numberChange/10 != 0){
            num10 = numberChange/10;
            numberChange = numberChange-(num10*10);
            this.changes.setB10(num10);
        }
        if(numberChange/5 != 0){
            num5 = numberChange/5;
            numberChange = numberChange-(num5*5);
            this.changes.setB5(num5);
        }
        this.changes.setB1(numberChange);

        return this.changes;
    }
}

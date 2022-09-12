package com.example.lab4;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MathAPI {
    @RequestMapping(value = "/plus/{n1}/{n2}", method = RequestMethod.GET)
    public String myPLus(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return Double.toString(num1+num2);
    }

    @RequestMapping(value = "/minus/{n1}/{n2}", method = RequestMethod.GET)
    public String myMinus(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return Double.toString(num1-num2);
    }

    @RequestMapping(value = "/divide/{n1}/{n2}", method = RequestMethod.GET)
    public String myDivide(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return Double.toString(num1/num2);
    }

    @RequestMapping(value = "/multi/{n1}/{n2}", method = RequestMethod.GET)
    public String myMulti(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return Double.toString(num1*num2);
    }

    @RequestMapping(value = "/mod/{n1}/{n2}", method = RequestMethod.GET)
    public String myMod(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return Double.toString(num1%num2);
    }

    @RequestMapping(value = "/max", method = RequestMethod.POST)
    public String myMax(@RequestBody MultiValueMap<String, String> n){
        Map<String, String> d = n.toSingleValueMap();
        double num1 = Double.parseDouble(d.get("num1"));
        double num2 = Double.parseDouble(d.get("num2"));
        return Double.toString(Math.max(num1, num2));
    }

}

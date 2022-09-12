package com.example.firstservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class MathService {
    @RequestMapping(value = "/add/{num1}/{num2}", method = RequestMethod.GET)
    public String add(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        return Double.toString(num1+num2);
    }
    @RequestMapping(value = "/minus/{num1}/{num2}", method = RequestMethod.GET)
    public String minus(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        return Double.toString(num1-num2);
    }
    @RequestMapping(value = "/multiply", method = RequestMethod.GET)
    public String multiply(@PathParam("num1") double num1, @PathParam("num2") double num2){
        return Double.toString(num1*num2);
    }
    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public String divide(@PathParam("num1") double num1, @PathParam("num2") double num2){
        return Double.toString(num1/num2);
    }
}

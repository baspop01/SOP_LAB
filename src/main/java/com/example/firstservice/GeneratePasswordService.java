package com.example.firstservice;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
public class GeneratePasswordService {
    @RequestMapping(path = "/{name:[a-z]+}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name){
        return "Hi, "+name+"\n"+"Your new password is "+((int)(Math.random()*999999999)-100000000);
    }
}

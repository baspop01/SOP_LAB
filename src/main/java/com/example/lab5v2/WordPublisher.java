package com.example.lab5v2;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words = new Word();


    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    public ArrayList addBad(@PathVariable("word") String s){
        this.words.badWords.add(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList delbad(@PathVariable("word") String s){
        for (String bad: this.words.badWords){
            if (bad.equals(s)){
                this.words.badWords.remove(s);
            }
        }
        return this.words.badWords;
    }
    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList addGood(@PathVariable("word") String s){
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList delGood(@PathVariable("word") String s){
        for (String good: this.words.goodWords){
            if (good.equals(s)){
                this.words.goodWords.remove(s);
            }
        }
        return this.words.goodWords;
    }

    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
    public String proofSentence(@PathVariable("sentence") String s){
        int badWord = 0;
        int goodWord = 0;
        String result = "";
        for (String bad: this.words.badWords){
            if (s.contains(bad)){
                badWord++;
            }
        }
        for (String good: this.words.goodWords){
            if (s.contains(good)){
                goodWord++;
            }
        }
        if (badWord > 0 && goodWord > 0){
            rabbitTemplate.convertAndSend("Fanout","", s);
            result = "Found Bad & Good Word";
        }else if (badWord > 0){
            rabbitTemplate.convertAndSend("Direct","bad", s);
            result = "Found Bad Word";
        }else if (goodWord > 0){
            rabbitTemplate.convertAndSend("Direct","good", s);
            result = "Found Good Word";
        }
        return result;
    }

    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){
        Object sentence = rabbitTemplate.convertSendAndReceive("Direct","sentence", "");
        return (Sentence) sentence;
    }

    @RequestMapping(value = "/addGoodWord", method = RequestMethod.POST)
    public ArrayList<String> addGoodWord(@RequestBody MultiValueMap<String, String> good){
        Map<String, String> wordGood = good.toSingleValueMap();
        this.words.goodWords.add(wordGood.get("goodWord"));
        return this.words.goodWords;
    }
    @RequestMapping(value = "/addBadWord", method = RequestMethod.POST)
    public ArrayList<String> addBadWord(@RequestBody MultiValueMap<String, String> bad){
        Map<String, String> wordBad = bad.toSingleValueMap();
        this.words.badWords.add(wordBad.get("badWord"));
        return this.words.badWords;
    }
    @RequestMapping(value = "/proofSentence", method = RequestMethod.POST)
    public String proofSentence2(@RequestBody MultiValueMap<String, String> s){
        Map<String, String> sen = s.toSingleValueMap();
        String sentence = sen.get("Sentence");
        int badWord = 0;
        int goodWord = 0;
        String result = "";
        for (String bad: this.words.badWords){
            if (sentence.contains(bad)){
                badWord++;
            }
        }
        for (String good: this.words.goodWords){
            if (sentence.contains(good)){
                goodWord++;
            }
        }
        if (badWord > 0 && goodWord > 0){
            rabbitTemplate.convertAndSend("Fanout","", sen.get("Sentence"));
            result = "Found Bad & Good Word";
        }else if (badWord > 0){
            rabbitTemplate.convertAndSend("Direct","bad", sen.get("Sentence"));
            result = "Found Bad Word";
        }else if (goodWord > 0){
            rabbitTemplate.convertAndSend("Direct","good", sen.get("Sentence"));
            result = "Found Good Word";
        }else{
            result = "Not Found any Bad or Good Word";
        }
        return result;
    }
}

package com.example.lab5v2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SentenceConsumer {
    public Sentence sentences = new Sentence();

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s){
        this.sentences.badSentence.add(s);
        System.out.println("In BadSentence Method : " +this.sentences.badSentence);
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s){
        this.sentences.goodSentence.add(s);
        System.out.println("In GoodSentence Method : " +this.sentences.goodSentence);
    }
    @RabbitListener(queues = "GetQueue")
    public Sentence getSentences(){
        return this.sentences;
    }
}

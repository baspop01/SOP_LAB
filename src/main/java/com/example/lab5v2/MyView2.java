package com.example.lab5v2;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.jsoup.internal.StringUtil;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;
import java.util.ArrayList;

@Route(value = "index2")
public class MyView2 extends FormLayout {

    public Word words = new Word();
    public MyView2(){
        VerticalLayout v1 = new VerticalLayout();
        VerticalLayout v2 = new VerticalLayout();
        HorizontalLayout hz = new HorizontalLayout();

        TextField addWord = new TextField("Add Word");
        Button btnGood = new Button("Add Good Word");
        Button btnBad = new Button("Add Bad Word");
        ComboBox<String> GoodBox = new ComboBox<>();
        GoodBox.setItems(this.words.goodWords);
        GoodBox.setLabel("GoodWords");
        ComboBox<String> BadBox = new ComboBox<>();
        BadBox.setItems(this.words.badWords);
        BadBox.setLabel("BadWords");
        v1.add(addWord, btnGood, btnBad, GoodBox, BadBox);

        TextField addSen = new TextField("Add Sentence");
        Button btnSen = new Button("Add Sentence");
        TextArea GoodSen = new TextArea("Good Sentences");
        TextArea BadSen = new TextArea("Bad Sentences");
        Button btnShow = new Button("Show Sentence");
        v2.add(addSen, btnSen, GoodSen, BadSen, btnShow);

        hz.add(v1, v2);
        add(hz);


        btnGood.addClickListener(buttonClickEvent -> {
           MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
           formData.add("goodWord", addWord.getValue());
           ArrayList out = WebClient.create()
                   .post()
                   .uri("http://localhost:8080/addGoodWord")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                   .body(BodyInserters.fromFormData(formData))
                   .retrieve()
                   .bodyToMono(ArrayList.class)
                   .block();
            System.out.println("Add Good Word Success");
            GoodBox.setItems(out);
        });

        btnBad.addClickListener(buttonClickEvent -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("badWord", addWord.getValue());
            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addBadWord")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            System.out.println("Add Bad Word Success");
            BadBox.setItems(out);
        });

        btnSen.addClickListener(buttonClickEvent -> {
           MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
           formData.add("Sentence", addSen.getValue());
           String out = WebClient.create()
                   .post()
                   .uri("http://localhost:8080/proofSentence")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                   .body(BodyInserters.fromFormData(formData))
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();
           System.out.println("Proof Sentence Success");
           new Notification(out, 5000).open();
        });

        btnShow.addClickListener(buttonClickEvent -> {
            Sentence out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();
            String good = StringUtil.join(out.goodSentence, ", ");
            String bad = StringUtil.join(out.badSentence, ", ");
            GoodSen.setValue("["+good+"]");
            BadSen.setValue("["+bad+"]");
        });
    }


}

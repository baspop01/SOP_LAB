package com.example.lab4;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Route(value = "index1")
public class MathView extends VerticalLayout {
    public MathView(){
        NumberField numtxt1 = new NumberField("Number 1");
        NumberField numtxt2 = new NumberField("Number 2");
        Label operator = new Label("Operator");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button divide = new Button("/");
        Button multi = new Button("x");
        Button mod = new Button("Mod");
        Button max = new Button("Max");
        NumberField ans = new NumberField("Answer");
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(plus, minus, divide, multi, mod, max);
        add(numtxt1, numtxt2, operator, hl, ans);

        plus.addClickListener(buttonClickEvent -> {
            double num1 = Double.parseDouble(Double.toString(numtxt1.getValue()));
            double num2 = Double.parseDouble(Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });
        minus.addClickListener(buttonClickEvent -> {
            double num1 = Double.parseDouble(Double.toString(numtxt1.getValue()));
            double num2 = Double.parseDouble(Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });
        divide.addClickListener(buttonClickEvent -> {
            double num1 = Double.parseDouble(Double.toString(numtxt1.getValue()));
            double num2 = Double.parseDouble(Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });
        multi.addClickListener(buttonClickEvent -> {
            double num1 = Double.parseDouble(Double.toString(numtxt1.getValue()));
            double num2 = Double.parseDouble(Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });
        mod.addClickListener(buttonClickEvent -> {
            double num1 = Double.parseDouble(Double.toString(numtxt1.getValue()));
            double num2 = Double.parseDouble(Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });

        max.addClickListener(buttonClickEvent -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("num1", Double.toString(numtxt1.getValue()));
            formData.add("num2", Double.toString(numtxt2.getValue()));
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max/")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(Double.parseDouble(out));
        });
    }
}

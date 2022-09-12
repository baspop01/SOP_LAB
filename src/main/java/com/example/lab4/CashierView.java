package com.example.lab4;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index2")
public class CashierView extends VerticalLayout {
    public CashierView(){
        NumberField changeNumber = new NumberField("เงินทอน");
        changeNumber.setPrefixComponent(new Span("$"));

        Button calculate = new Button("คำนวณเงินทอน");

        NumberField b1000 = new NumberField();
        b1000.setPrefixComponent(new Span("$1000:"));
        NumberField b500 = new NumberField();
        b500.setPrefixComponent(new Span("$500:"));
        NumberField b100 = new NumberField();
        b100.setPrefixComponent(new Span("$100:"));
        NumberField b20 = new NumberField();
        b20.setPrefixComponent(new Span("$20:"));
        NumberField b10 = new NumberField();
        b10.setPrefixComponent(new Span("$10:"));
        NumberField b5 = new NumberField();
        b5.setPrefixComponent(new Span("$5:"));
        NumberField b1 = new NumberField();
        b1.setPrefixComponent(new Span("$1:"));

        add(changeNumber, calculate, b1000, b500, b100, b20, b10, b5, b1);

        calculate.addClickListener(buttonClickEvent -> {
            int num = changeNumber.getValue().intValue();
            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+num)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();

            b1000.setValue((double) out.getB1000());
            b500.setValue((double) out.getB500());
            b100.setValue((double) out.getB100());
            b20.setValue((double) out.getB20());
            b10.setValue((double) out.getB10());
            b5.setValue((double) out.getB5());
            b1.setValue((double) out.getB1());

        });

    }



}

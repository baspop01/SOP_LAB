package com.example.firstservice;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;

    public Customer(){
        this.ID = "";
        this.name = null;
        this.sex = false;
        this.age = 0;
    }
    public Customer(String ID, String n, String s, int a){
        this.ID = ID;
        this.name = n;
        if(s == "Male" || s == "male"){
            this.sex = true;
        }else if(s == "Female" || s == "female"){
            this.sex = false;
        }
        this.age = a;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public boolean getSex(){
        return sex;
    }

    public void setSex(String sex){
        if(sex == "Male" || sex == "male"){
            this.sex = true;
        }else if(sex == "Female" || sex == "female"){
            this.sex = false;
        }
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
}

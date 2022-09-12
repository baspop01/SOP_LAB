package com.example.firstservice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customers;
    public CustomerController(){
        this.customers = new ArrayList<Customer>();
        this.customers.add(new Customer("1010", "John", "Male", 25));
        this.customers.add(new Customer("1018", "Peter", "Male", 24));
        this.customers.add(new Customer("1019", "Sara", "Female", 23));
        this.customers.add(new Customer("1110", "Rose", "Female", 23));
        this.customers.add(new Customer("1001", "Emma", "Female", 24));
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        return this.customers;
    }

    @RequestMapping(value = "/customerbyid/{id}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable("id") String ID){
        for (Customer customer: customers){
            if (customer.getID().equals(ID)){
                return customer;
            }
        }
        return null;
    }

    @RequestMapping(value = "/customerbyname/{name}", method = RequestMethod.GET)
    public Customer getCustomerByName(@PathVariable("name") String n){
        for (Customer customer: customers){
            if (customer.getName().equals(n)){
                return customer;
            }
        }
        return null;
    }

    @RequestMapping(value = "/customerDelByid/{id}", method = RequestMethod.DELETE)
    public boolean delCustomerByID(@PathVariable("id") String ID){
        for (Customer customer: customers){
            if (customer.getID().equals(ID)) {
                this.customers.remove(customer);
            }
        }

        return true;
    }

    @RequestMapping(value = "/customerDelByName/{name}", method = RequestMethod.DELETE)
    public boolean delCustomerByName(@PathVariable("name") String n){
        for (Customer customer: customers){
            if (customer.getName().equals(n)) {
                this.customers.remove(customer);
            }
        }

        return true;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public boolean addCustomer(@RequestParam("ID") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,
                               @RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }

    @RequestMapping(value = "/addCustomer2", method = RequestMethod.POST)
    public boolean addCustomer2(@RequestParam("ID") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,
                               @RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }
}

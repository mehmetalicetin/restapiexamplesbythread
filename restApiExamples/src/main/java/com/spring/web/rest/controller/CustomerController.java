package com.spring.web.rest.controller;

import com.spring.web.rest.model.Customer;
import com.spring.web.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.Collection;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON)
    public Collection<Customer> findAll(){
       return customerService.findAll();
    }

}

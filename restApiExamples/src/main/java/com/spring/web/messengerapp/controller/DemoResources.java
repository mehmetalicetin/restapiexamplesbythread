package com.spring.web.messengerapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping(path = "/demo")
public class DemoResources {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN)
    public String getMessage(){
        return "Hello World!";
    }
}

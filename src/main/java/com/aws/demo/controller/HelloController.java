package com.aws.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String getMessage(){
        return "Hello, Welcome to AWS learning.";
    }

    @GetMapping("/{name}")
    public String  getMessage(@PathVariable(name = "name") String name){
        return "Hello " + name + ", Welcome to AWS learning.";
    }
}

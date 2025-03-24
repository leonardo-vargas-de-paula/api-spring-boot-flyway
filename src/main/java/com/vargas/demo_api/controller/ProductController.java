package com.vargas.demo_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {


    //teste
    public String hello(){
        return "Hello World\n";
    }
}

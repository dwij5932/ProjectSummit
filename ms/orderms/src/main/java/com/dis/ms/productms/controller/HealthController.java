package com.dis.ms.productms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HealthController {
    @GetMapping
    public String getTest(){
        return "Welcome to the Product Micro service";
    }
}

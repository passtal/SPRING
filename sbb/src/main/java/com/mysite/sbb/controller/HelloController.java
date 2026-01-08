package com.mysite.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HelloController {
    
    @ResponseBody
    @GetMapping("/jump")
    public String hello() {
        return "점프 투 스프링부트";
    }
    
}

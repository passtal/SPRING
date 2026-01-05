package com.aloha.spring_di2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aloha.spring_di2.service.PostService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Slf4j
@Controller
public class HomeController {
    
    @Autowired
    // @Qualifier("boardServiceImpl")  // 빈 이름을 지정하여 주입할 빈을 명시
    // @Qualifier("commentServiceImpl")
    // @Qualifier("B")
    // @Qualifier("C")
    private PostService postService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        postService.list();
        return "index";
    }
    
}
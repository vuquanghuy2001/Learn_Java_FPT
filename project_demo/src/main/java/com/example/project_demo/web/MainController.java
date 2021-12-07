package com.example.project_demo.web;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

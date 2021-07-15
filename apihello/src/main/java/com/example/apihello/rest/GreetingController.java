package com.example.apihello.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String teamplate = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public com.example.apihello.rest.Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name ){
        return new com.example.apihello.rest.Greeting(counter.incrementAndGet(), String.format(teamplate, name));
    }
}

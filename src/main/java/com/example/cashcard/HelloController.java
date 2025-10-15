package com.example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("")
    public ResponseEntity<Hello> sayHello() {
        return ResponseEntity.ok(new Hello("Hello, world!", LocalDateTime.now()));
    }
}

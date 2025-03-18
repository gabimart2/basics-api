package com.example.basics.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BaiscController {

    @GetMapping("/basics")
    public ResponseEntity<String> getBasics(){
        return ResponseEntity.ok("Hello World!");
    }

}

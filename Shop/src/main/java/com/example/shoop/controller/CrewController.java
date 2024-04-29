package com.example.shoop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CrewController {

    //@PreAuthorize("hasRole('CREW')")
    @GetMapping("/crew")
    public String crew(){
        return "Hello Crew!";
    }
}

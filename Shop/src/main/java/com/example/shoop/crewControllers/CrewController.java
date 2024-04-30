package com.example.shoop.crewControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class CrewController {

    //@PreAuthorize("hasRole('CREW')")
    @GetMapping("/crew")
    @ResponseBody
    public String crew(){
        return "Hello Crew!";
    }
}

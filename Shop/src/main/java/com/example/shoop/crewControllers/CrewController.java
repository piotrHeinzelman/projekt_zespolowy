package com.example.shoop.crewControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CrewController {

    //@PreAuthorize("hasRole('CREW')")
    @RequestMapping( value={ "/crew" } , method = RequestMethod.GET )
    @ResponseBody
    public String crew(){
        return "Hello Crew!";
    }
}

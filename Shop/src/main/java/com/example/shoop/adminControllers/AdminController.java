package com.example.shoop.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @RequestMapping( value={ "/admin" } , method = RequestMethod.GET )
    @ResponseBody
    public String admin(){
        return "Hello Admin!";
    }

}

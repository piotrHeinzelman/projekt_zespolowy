package com.example.shoop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private PasswordEncoder passwordEncoder;


    @GetMapping()
    //@ResponseBody
    public String home(){
        return "index";
    }


    @RequestMapping( value={ "/user/add" } , method = RequestMethod.GET )
    public String homeAddUser(){
        //                <form  action="@{'/add_user'}" th:action="@{'/add_user'}"   method="post"  enctype="multipart/form-data">
    return "adduser";
    }


    @RequestMapping( value={ "/user/add" } , method = RequestMethod.POST )
    public String editItem_POST( Model model , @RequestParam Map<String,String> paramMap ) {

        String newUserMail = paramMap.get("mail");
        String pass1 = paramMap.get("pass");
        String pass2 = paramMap.get("pass2");
        if (!pass1.equals(pass2)) { model.addAttribute("errorMsg","niezgodne hasła"); return "index"; }
        if (pass1.length()==0) { model.addAttribute("errorMsg","puste hasło"); return "index"; }
        if ( users.userExists(  newUserMail ) ) { model.addAttribute("errorMsg","adres mail wykorzystany w systemie"); return "index"; }
        if ( !newUserMail.contains("@") ) { model.addAttribute("errorMsg","podaj poprawny adres mailowy"); return "index"; }


        UserDetails newUser = User.builder()
                .username( newUserMail )
                .password(passwordEncoder.encode( pass1 ))
                .roles( "USER")
                .build();

        users.createUser( newUser );

        model.addAttribute("success","dodano uzytkownika: " + newUserMail );

        return "index";
    }

}

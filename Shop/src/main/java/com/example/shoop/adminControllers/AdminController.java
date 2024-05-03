package com.example.shoop.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired private AccountService accountService;

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private UserDetailsService userDetailsService;

    @RequestMapping( value={ "/admin" } , method = RequestMethod.GET )
    @ResponseBody
    public String admin(){
        return "Hello Admin!";
    }

    @RequestMapping( value={ "/admin/user/list" } , method = RequestMethod.GET )
    public String homeUserInfo( Model model ){
        model.addAttribute("userNames", accountService.getAllUserName() );
        return "/user/list";
    }

    @RequestMapping( value={ "/admin/user/edit/{username}" } , method = RequestMethod.GET )
    public String homeUserInfo( @PathVariable String username,  Model model ){
        System.out.println( username );
        model.addAttribute("user", userDetailsService.loadUserByUsername( username ) );
        System.out.println( userDetailsService.loadUserByUsername( username ));
        return "/user/edit";
    }

}

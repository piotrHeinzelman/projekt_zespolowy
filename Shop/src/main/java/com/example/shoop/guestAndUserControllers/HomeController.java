package com.example.shoop.guestAndUserControllers;

import com.example.shoop.adminControllers.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;


    @RequestMapping( value={ "/","","/index","index","index.php","index.asp","/index.php","/index.asp" } ) // , method = RequestMethod.POST
    public String home(){
        return "index";
    }


    @RequestMapping( value={ "/user/add" } , method = RequestMethod.GET )
    public String homeAddUser(){
        return "adduser";
    }

    @RequestMapping( value={ "/user/add" } , method = RequestMethod.POST )
    public String editItem_POST( Model model , @RequestParam Map<String,String> paramMap ) {

        String newUserMail = paramMap.get("mail");
        String pass1 = paramMap.get("pass");
        String pass2 = paramMap.get("pass2");

        try {
            accountService.addUser(newUserMail, pass1, pass2);
        } catch ( Throwable th ){ model.addAttribute("errorMsg", th.getMessage() ); return "index"; }

        model.addAttribute("success","dodano uzytkownika: " + newUserMail );
        return "index";
    }

    @RequestMapping( value={ "/user/info" } , method = RequestMethod.GET )
    public String homeUserInfo( Model model ){
        model.addAttribute("success", accountService.getUserGroup() );
        return "index";
    }

    @RequestMapping( value={ "/login" } , method = RequestMethod.GET )
    String login() {
        return "login";
    }

}

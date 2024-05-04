package com.example.shoop.guestAndUserControllers;

import com.example.shoop.adminControllers.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
        return "user/add";
    }

    @RequestMapping( value={ "/user/add" } , method = RequestMethod.POST )
    public String homeAddUserPOST( Model model , @RequestParam Map<String,String> paramMap ) {
        try {
            accountService.addUser(paramMap);
        } catch ( Throwable th ){ model.addAttribute("errorMsg", th.getMessage() ); return "/user/add"; }
        return "index";
    }




    @RequestMapping( value={ "/user/login" } , method = RequestMethod.GET )
    public String login() {
        return "user/login";
    }




    @RequestMapping( value={ "/user/logout" } , method = RequestMethod.GET )
    public String logout( HttpServletRequest request, HttpServletResponse response ) {
        accountService.logout( request, response );
        return "redirect:";
    }



    @RequestMapping( value={ "/user/info" } , method = RequestMethod.GET )
    public String homeUserInfo( Model model ){
        model.addAttribute("success", accountService.getUserGroup() );
        return "index";
    }



}

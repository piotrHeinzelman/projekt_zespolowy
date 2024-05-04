package com.example.shoop.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        model.addAttribute("user_name", userDetails.getUsername() );
        model.addAttribute("user_enabled", userDetails.isEnabled() );

        //Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Set<String> strings = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());

        HashMap<String, Boolean > userGrants = new HashMap<>(4);
        for ( String gName : Set.of( "ROLE_USER","ROLE_CREW","ROLE_ADMIN" ) ){
            userGrants.put( gName , strings.contains( gName ));
        }
        model.addAttribute("user_grants", userGrants );

        // AuthorityUtils
        // AuthorityUtils.createAuthorityList("ROLE_USER" );

        // System.out.println( " ROLE_USER ? : " + authorities.contains( AuthorityUtils.createAuthorityList("ROLE_USER" ).get(0) ) );

        /*
        strings: [ROLE_CREW, ROLE_USER]
{ROLE_CREW=true, ROLE_ADMIN=false, ROLE_USER=true}

         */

        return "/user/edit";
    }


    @RequestMapping( value={ "/admin/user/edit" } , method = RequestMethod.POST )
    public String homeAddUserPOST( Model model , @RequestParam Map<String,String> paramMap ) {
        System.out.println( paramMap );
        return "redirect:/admin/user/list";
    }

}

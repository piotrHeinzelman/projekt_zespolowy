package com.example.shoop.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.*;

@Controller
public class AdminController {

    @Autowired private AccountService accountService;

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private UserDetailsService userDetailsService;
    private static Set<String> allGrant=Set.of( "ROLE_USER","ROLE_CREW","ROLE_ADMIN" );

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
        if (!users.userExists( username )) { return "redirect:/admin/user/list"; }
        UserDetails userDetails = null;
        try {
            userDetails = users.loadUserByUsername(username);
        } catch (Throwable th) {}

        model.addAttribute("user_name", username );
        model.addAttribute("user_enabled", (userDetails==null) ? false : userDetails.isEnabled() );
        Set<String> strings=Set.of("ROLE_USER");
        if ( userDetails!=null  ) {
            strings = AuthorityUtils.authorityListToSet(userDetails.getAuthorities()); //Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        }
        HashMap<String, Boolean > userGrants = new HashMap<>(4);
        for ( String gName : allGrant ){
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
        String userName=paramMap.get("username");
        UserDetails userDetails = null;
        if ( users.userExists( userName )){
            try { userDetails = userDetailsService.loadUserByUsername( userName );
            } catch(Throwable t){ }
            Set<String> authOld = Set.of("ROLE_USER");
            if ( userDetails!=null ) {
                authOld = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());
            }
            for ( String gname : allGrant  ){
                if ( paramMap.keySet().contains( gname ) && authOld.contains( gname ) ) { continue; }
                if ( paramMap.keySet().contains( gname ) && !authOld.contains( gname ) ) { /* add */ addGrant(  userName, gname ); continue; }
                if ( !paramMap.keySet().contains( gname ) && authOld.contains( gname ) ) { /* remove */ removeGrant(  userName,  gname ); continue; }
            }
            setEnabled( userName , paramMap.keySet().contains( "enabled" ) );
        }
        return "redirect:/admin/user/list";
    }

    private void addGrant( String userName, String grantName ){
        accountService.addGrant(userName,grantName);
    }

    private void removeGrant( String userName, String grantName ){
        accountService.removeGrant(userName,grantName);
    }

    private void setEnabled( String userName, Boolean enabled ){
        accountService.setEnabled( userName, enabled );
    }



    @RequestMapping( value={ "/admin/user/delete" } , method = RequestMethod.POST )
    public String UserDeletePOST( Model model , @RequestParam Map<String,String> paramMap ) {
        String userName=paramMap.get("username");
        if (users.userExists( userName )){
            users.deleteUser( userName );
        }
        return "redirect:/admin/user/list";
    }
}

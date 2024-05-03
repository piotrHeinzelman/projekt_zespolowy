package com.example.shoop.adminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private PasswordEncoder passwordEncoder;


    public void addUser( String newUserMail, String pass1, String pass2 ) throws Error {

        if (!pass1.equals(pass2)) { throw new VerifyError("niezgodne hasła"); }
        if (pass1.length()==0) { throw new VerifyError("puste hasło"); }
        if ( users.userExists(  newUserMail ) ) { throw new VerifyError("adres mail wykorzystany w systemie");  }
        if ( !newUserMail.contains("@") ) { throw new VerifyError("podaj poprawny adres mailowy"); }

        UserDetails newUser = User.builder()
                .username( newUserMail )
                .password( passwordEncoder.encode( pass1 ) )
                .roles( "USER")
                .build();

        users.createUser( newUser );
    }

    public String getUserGroup(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // AuthorityUtils
        // AuthorityUtils.createAuthorityList("ROLE_USER" );

        // System.out.println( " ROLE_USER ? : " + authorities.contains( AuthorityUtils.createAuthorityList("ROLE_USER" ).get(0) ) );


        return ""+currentPrincipalName + " has roles: " + authorities.toString() ;
    }

}

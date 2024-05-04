package com.example.shoop.adminControllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Service
public class AccountService {

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserDetailsService userDetailsService;


    public void addUser( Map<String,String> paramMap ) throws Error {

        String newUserMail = paramMap.get("username");
        String pass1 = paramMap.get("password");
        String pass2 = paramMap.get("password2");

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

    public void logout( HttpServletRequest request, HttpServletResponse response ){
            Authentication auth =
                    SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout( request, response, auth );
            }
    }




    private final List<String> executeQueryOnUserDb( String Query ){
        List<String> names = new ArrayList<>(10);
        try {
            DataSource dataSource = users.getDataSource();
            Connection connection = dataSource.getConnection() ;
            Statement stmt = null;
            ResultSet rs = null;
            stmt = connection.createStatement();
            rs = stmt.executeQuery( Query );
            while(rs.next()){
                names.add( rs.getString(1 ) );
            }
            connection.close();
        } catch(Throwable th) { /* System.out.println( th ) */ ; }
        return names;
    }


    private final List<String> executeQueryOnUserDb2( String Query ){
        List<String> names = new ArrayList<>(10);
        try {
            DataSource dataSource = users.getDataSource();
            Connection connection = dataSource.getConnection() ;
            Statement stmt = connection.createStatement();
            stmt.execute( Query );
            connection.close();
        } catch(Throwable th) { /* System.out.println( th ) */ ; }
        return names;
    }


    public List<String> getAllUserName(){
        return executeQueryOnUserDb( " SELECT USERNAME FROM USERS  " );
    }

    public List<String> addGrant( String userName, String grantName ){
        return executeQueryOnUserDb2( " INSERT INTO AUTHORITIES  VALUES ( '"+userName+"' ,  '"+grantName+"' ); " );
        //INSERT INTO AUTHORITIES  VALUES ( 'a@b.pl' ,  'ROLE_USER' );
    }

    public List<String> removeGrant( String userName, String grantName ){
        String s=" DELETE FROM AUTHORITIES  WHERE USERNAME='"+userName+"' AND AUTHORITY='"+grantName+"' ";
        return executeQueryOnUserDb2( s );
        //DELETE FROM AUTHORITIES  WHERE USERNAME='a@b.pl' AND AUTHORITY='ROLE_USER'
    }

    public List<String> setEnabled( String userName, Boolean enable ){
        return executeQueryOnUserDb2( " UPDATE USERS  SET ENABLED="+enable+" WHERE USERNAME='"+userName+"'; " );
        //UPDATE USERS  SET ENABLED=0 WHERE USERNAME='a@a'
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

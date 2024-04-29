package com.example.shoop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    JdbcUserDetailsManager users( DataSource dataSource , PasswordEncoder encoder ){
    /*
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode( "admin"))
                .roles("ADMIN", "USER", "CREW")
                .build();
        UserDetails crew = User.builder()
                .username("crew")
                .password(encoder.encode( "crew"))
                .roles( "USER", "CREW")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode( "user"))
                .roles( "USER")
                .build();
        */

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager( dataSource );
  //      jdbcUserDetailsManager.createUser( admin );
   //     jdbcUserDetailsManager.createUser( crew );
  //      jdbcUserDetailsManager.createUser( user );

        return jdbcUserDetailsManager;
    }

    /*
    @Bean
    EmbeddedDatabase dataSource(){
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .setName("dashboard")
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();

    }
     */

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


/*
    @Bean
    InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build()
        );
    }
*/

    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        return http
                .csrf(csrf-> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeRequests( auth->auth

                        .requestMatchers( "/h2-console/**" ).permitAll()
                        .requestMatchers( "/admin/**" ).hasAnyRole("ADMIN")
                        .requestMatchers( "/crew/**" ).hasAnyRole("CREW")
                        .requestMatchers( "/**" ).permitAll()
                        //.anyRequest().authenticated()
                )
                .headers(headers->headers.frameOptions().sameOrigin())
                .formLogin(withDefaults())
                .build();
    }
}

// ** H2 **
// RUNSCRIPT FROM 'test.sql'  <- load from SQL
// SCRIPT TO 'fileName'  <- DUMP
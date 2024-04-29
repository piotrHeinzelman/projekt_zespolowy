package com.example.Shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig {
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user")
                .password(passwordEncoder().encode("password")).roles("USER");
    }
*/

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource( dataSource )
                .withDefaultSchema()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
    }





    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/product").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/*").permitAll()
                        .requestMatchers("*").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf().disable();

        return http.build();
    }

}







/*

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigNew {



        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/product").permitAll()
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/api/*").permitAll()
                            .requestMatchers("*").permitAll()
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults())
                    .formLogin(Customizer.withDefaults())
                    .csrf().disable();


            return http.build();
        }




        @Bean
        public AuthenticationManager authenticationManager(
                UserDetailsService userDetailsService,
                PasswordEncoder passwordEncoder) {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);

            ProviderManager providerManager = new ProviderManager(authenticationProvider);
            providerManager.setEraseCredentialsAfterAuthentication(false);

            return providerManager;
        }

        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails userP = User.withDefaultPasswordEncoder().username("paulina").password("paulina").roles("USER").build();
            UserDetails userB = User.withDefaultPasswordEncoder().username("bartek").password("bartek").roles("USER").build();
            UserDetails userH = User.withDefaultPasswordEncoder().username("piotr").password("piotr").roles("USER").build();
            UserDetails userC = User.withDefaultPasswordEncoder().username("crew").password("crew").roles("USER").build();
            UserDetails userA = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER","ADMIN","CREW","STORE").build();
            UserDetails userS = User.withDefaultPasswordEncoder().username("stanislaw").password("stanislaw").roles("STORE").build(); //https://www.youtube.com/watch?v=yh5JkIz2X-U

            return new InMemoryUserDetailsManager(userP,userB,userH,userC,userA,userS);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

}
*/
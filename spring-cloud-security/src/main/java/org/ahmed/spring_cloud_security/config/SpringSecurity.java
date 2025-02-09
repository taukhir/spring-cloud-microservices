package org.ahmed.spring_cloud_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //auth any request
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //removing session and making it stateless for API's
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        //basic authentication but its deprecated now
//        http.httpBasic();
//        http.csrf().disable();
        return http.build();
    }

    //in memory db for storing user credentials
    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("ahmed")
                .password("{noop}ahmed@777")
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password("{noop}ahmed@777")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, admin);
    }
}

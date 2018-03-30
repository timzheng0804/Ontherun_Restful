package com.ontherun.restful_api.config;

import com.ontherun.restful_api.security.CustomAuthenticationProvider;
import com.ontherun.restful_api.security.JWTAuthenticationFilter;
import com.ontherun.restful_api.security.JWTLoginFilter;
import com.ontherun.restful_api.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()                           // Allow all to welcome page
                .antMatchers(HttpMethod.POST, "/login").permitAll()     // Allow user to login
                .antMatchers(HttpMethod.POST, "/users").permitAll()     // Allow user creation
                .antMatchers("/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)                 // Login filter, check if login info valid
                .addFilterBefore(new JWTAuthenticationFilter(),                     // Check JWT token for all other accesses
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Use Custom Authentication
        auth.authenticationProvider(new CustomAuthenticationProvider(userService));
    }
}

package com.udacity.jwdnd.course1.cloudstorage.core.config;

import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/h2-console/**","/login/**", "/signup/**", "/css/**", "/js/**")
            .permitAll()
            .anyRequest()
            .authenticated().and().formLogin().failureHandler(failureHandler)
            .and().logout().logoutSuccessUrl("/login?logout")
            .and().csrf().disable();

        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home", true);
    }
}

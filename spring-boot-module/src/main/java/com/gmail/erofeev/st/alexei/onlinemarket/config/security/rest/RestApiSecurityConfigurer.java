package com.gmail.erofeev.st.alexei.onlinemarket.config.security.rest;

import com.gmail.erofeev.st.alexei.onlinemarket.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@Order(1)
public class RestApiSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final SecurityProperties securityProperties;

    @Autowired
    @Qualifier("apiAccessDeniedHandler")
    private AccessDeniedHandler apiAccessDeniedHandler;

    @Autowired
    public RestApiSecurityConfigurer(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, SecurityProperties securityProperties) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/v1/**")
                .authorizeRequests()
                .antMatchers("/api/v1/users", "/api/v1/articles")
                .hasRole(securityProperties.getRoleSecureRestApi())
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedHandler(apiAccessDeniedHandler)
                .and()
                .csrf()
                .disable();
    }
}
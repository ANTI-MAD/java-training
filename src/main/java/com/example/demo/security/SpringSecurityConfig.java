package com.example.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoadUserDetailService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/beer-shop-app/user/sign-in", "/beer-shop-app/user/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/beer-shop-app/beers/**").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/beer-shop-app/beers/*").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/beer-shop-app/beers/*").hasAnyRole(UserRole.CUSTOMER.name(), UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/beer-shop-app/orders/*").hasRole(UserRole.CUSTOMER.name())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

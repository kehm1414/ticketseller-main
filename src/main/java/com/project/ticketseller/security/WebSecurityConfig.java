package com.project.ticketseller.security;

import com.project.ticketseller.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Autowired
  private CustomOAuth2UserService oAuth2UserService;

  @Autowired
  private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and()
            .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/oauth2/**").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/assets/**").permitAll()
        .antMatchers("/forms/**").permitAll()
        .antMatchers("/event-details/*").permitAll()
        .antMatchers("/event-categories/**").hasRole("ADMIN")
        .antMatchers("/administrate").hasRole("ADMIN")
        .antMatchers("/events/**").hasRole("ADMIN")
        .antMatchers("/venues/**").hasRole("ADMIN")
        .antMatchers("/my-tickets").hasRole("USER")
        .antMatchers("/tickets/*/process_ticket_purchase").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .usernameParameter("email")
          .permitAll()
        .and()
        .oauth2Login()
          .loginPage("/login")
          .userInfoEndpoint()
          .userService(oAuth2UserService)
          .and()
          .successHandler(oAuth2LoginSuccessHandler)
        .and()
          .logout()
          .permitAll()
        .and()
          .exceptionHandling()
          .accessDeniedPage("/403");
  }
}

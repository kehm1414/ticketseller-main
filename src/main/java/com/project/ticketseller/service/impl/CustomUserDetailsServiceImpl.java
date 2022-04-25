package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.CustomUserDetails;
import com.project.ticketseller.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserServiceImpl userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }
    return new CustomUserDetails(user);
  }
}

package com.project.ticketseller.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDetails implements UserDetails, GlobalUser {

  private User user;

  public CustomUserDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    return Arrays.asList(authority);
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }

  public Long getId() {
    return user.getId();
  }

  public String getFullName() {
    return user.getFirstName() + " " + user.getLastName();
  }

  public String getEmail() {
    return user.getUsername();
  }

  public String getFirstName() {
    return user.getFirstName();
  }
}
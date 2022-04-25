package com.project.ticketseller.service;

import com.project.ticketseller.entity.AuthenticationProvider;
import com.project.ticketseller.entity.User;

public interface UserService {

  User getUserByUsername(String username);

  User getById(Long id);

  User registerUser(User user);

  User createNewUserAfterOAuthLoginSuccess(
      String email, String name, AuthenticationProvider provider);

  User updateCustomerAfterOAuthLoginSuccess(
      User user, String name, AuthenticationProvider provider);
}

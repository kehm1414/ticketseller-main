package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.AuthenticationProvider;
import com.project.ticketseller.entity.User;
import com.project.ticketseller.helpers.PasswordEncoder;
import com.project.ticketseller.repository.UserRepository;
import com.project.ticketseller.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(User user) {
    user.setPassword(PasswordEncoder.generate(user.getPassword()));
    user.setCreatedOn(Date.valueOf(LocalDate.now()));
    user.setRole("ROLE_USER");
    user.setEnabled(true);
    user.setAuthProvider(AuthenticationProvider.LOCAL);
    return userRepository.save(user);
  }

  @Override
  public User createNewUserAfterOAuthLoginSuccess(
      String email, String name, AuthenticationProvider provider) {
    User user = new User();
    user.setUsername(email);
    user.setFirstName(name);
    user.setEnabled(true);
    user.setCreatedOn(Date.valueOf(LocalDate.now()));
    user.setRole("ROLE_USER");
    user.setAuthProvider(provider);

    return userRepository.save(user);
  }

  @Override
  public User updateCustomerAfterOAuthLoginSuccess(
      User user, String name, AuthenticationProvider provider) {
    user.setFirstName(name);
    user.setAuthProvider(provider);
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.getUserByUsername(username);
  }

  @Override
  public User getById(Long id) {
    return userRepository.getById(id);
  }
}

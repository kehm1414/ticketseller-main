package com.project.ticketseller.helpers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

  public static void main(String[] args) {
    System.out.println(generate("user"));
  }

  public static String generate(String rawPassword) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(rawPassword);
  }
}

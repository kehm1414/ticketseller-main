package com.project.ticketseller.controller;

import com.project.ticketseller.entity.User;
import com.project.ticketseller.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SessionController {

  private UserService userService;

  public SessionController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String loginPage() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
      return "login";
    }

    return "redirect:/";
  }

  @GetMapping("/register")
  public String registerPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute("user") User user) {
    userService.registerUser(user);
    return "redirect:/register?register_success";
  }
}

package com.project.ticketseller.controller;

import com.project.ticketseller.entity.GlobalUser;
import com.project.ticketseller.entity.Ticket;
import com.project.ticketseller.entity.User;
import com.project.ticketseller.service.EventService;
import com.project.ticketseller.service.TicketService;
import com.project.ticketseller.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TicketController {

  TicketService ticketService;
  UserService userService;
  EventService eventService;

  public TicketController(
      TicketService ticketService, UserService userService, EventService eventService) {
    this.ticketService = ticketService;
    this.userService = userService;
    this.eventService = eventService;
  }

  @PostMapping("/tickets/{id}/process_ticket_purchase")
  public String processTicketPurchase(
      @PathVariable Long id, @AuthenticationPrincipal GlobalUser user) {
    Ticket ticket =
        ticketService.save(
            eventService.getById(id), userService.getUserByUsername(user.getUsername()));
    return "redirect:/tickets/" + ticket.getId();
  }

  @GetMapping("/tickets/{id}")
  public String showTicket(@PathVariable Long id, Model model) {
    model.addAttribute("ticket", ticketService.getById(id));
    return "ticket-details";
  }

  @GetMapping("/my-tickets")
  public String myTickets(@AuthenticationPrincipal GlobalUser user, Model model) {
    User currUser = userService.getUserByUsername(user.getUsername());
    List<Ticket> tickets = currUser.getTickets();
    model.addAttribute("tickets", tickets);
    return "my-tickets";
  }
}

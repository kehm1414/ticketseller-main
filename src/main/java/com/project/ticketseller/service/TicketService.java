package com.project.ticketseller.service;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.entity.Ticket;
import com.project.ticketseller.entity.User;

public interface TicketService {

  Ticket save(Event event, User user);

  Ticket getById(Long id);
}

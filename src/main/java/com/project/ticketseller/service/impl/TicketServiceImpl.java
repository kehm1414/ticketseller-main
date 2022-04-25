package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.entity.Ticket;
import com.project.ticketseller.entity.User;
import com.project.ticketseller.helpers.SerialKeyGenerator;
import com.project.ticketseller.repository.TicketRepository;
import com.project.ticketseller.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {

  private TicketRepository ticketRepository;

  @Autowired
  public TicketServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public Ticket save(Event event, User user) {
    Ticket ticket = new Ticket();
    ticket.setSerialKey(SerialKeyGenerator.generate(10));
    ticket.setEvent(event);
    ticket.setUser(user);
    ticketRepository.save(ticket);
    return ticket;
  }

  @Override
  public Ticket getById(Long id) {
    return ticketRepository.getById(id);
  }
}

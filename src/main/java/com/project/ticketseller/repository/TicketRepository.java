package com.project.ticketseller.repository;

import com.project.ticketseller.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}

package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.entity.Ticket;
import com.project.ticketseller.entity.User;
import com.project.ticketseller.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {

    @MockBean
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void testSaveTicket(@Mock Event event, @Mock User user){
        Ticket savedTicket = ticketService.save(event, user);
        assertNotNull(savedTicket);
    }



}
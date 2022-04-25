package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.entity.Ticket;
import com.project.ticketseller.entity.User;
import com.project.ticketseller.entity.Venue;
import com.project.ticketseller.helpers.testOnly.WithMockCustomUser;
import com.project.ticketseller.repository.UserRepository;
import com.project.ticketseller.security.CustomOAuth2UserService;
import com.project.ticketseller.security.OAuth2LoginSuccessHandler;
import com.project.ticketseller.service.impl.EventServiceImpl;
import com.project.ticketseller.service.impl.TicketServiceImpl;
import com.project.ticketseller.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
class TicketControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketServiceImpl ticketService;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private EventServiceImpl eventService;

    @MockBean
    private CustomOAuth2UserService oAuth2UserService;
    @MockBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @MockBean
    private UserRepository userRepository;


    @Test
    @WithMockCustomUser(role = "ROLE_USER")
    void processTicketPurchase() throws Exception {

        String url = "/tickets/1/process_ticket_purchase";

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(ticketService.save(eventService.getById(1l), userService.getUserByUsername("mockName"))).thenReturn(mockTicket);
        Mockito.when(mockTicket.getId()).thenReturn(1l);

        ResultActions perform = mockMvc.perform(post(url).with(csrf()));
        perform.andDo(print()).andExpect((status().is3xxRedirection()));
    }

    @Test
    @WithMockCustomUser(role = "ROLE_USER")
    void showTicket() throws Exception {
        String url = "/tickets/1";

        Ticket ticket = Mockito.mock(Ticket.class);
        Event event = Mockito.mock(Event.class);
        User user = Mockito.mock(User.class);
        Venue venue = Mockito.mock(Venue.class);
        Mockito.when(ticketService.getById(1l)).thenReturn(ticket);
        Mockito.when(ticket.getEvent()).thenReturn(event);
        Mockito.when(ticket.getUser()).thenReturn(user);
        Mockito.when(event.getVenue()).thenReturn(venue);

        ResultActions perform = mockMvc.perform(get(url).flashAttr("ticket", ticket).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser(role = "ROLE_USER")
    void myTickets() throws Exception {

        String url = "/my-tickets";

        User user = Mockito.mock(User.class);
        List<Ticket> mockTickets = new ArrayList<>();

        Mockito.when(userService.getUserByUsername("MockCustomUser")).thenReturn(user);
        Mockito.when(user.getTickets()).thenReturn(mockTickets);

        ResultActions perform = mockMvc.perform(get(url).flashAttr("tickets", mockTickets).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }
}
package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Venue;
import com.project.ticketseller.helpers.testOnly.WithMockCustomUser;
import com.project.ticketseller.repository.UserRepository;
import com.project.ticketseller.security.CustomOAuth2UserService;
import com.project.ticketseller.security.OAuth2LoginSuccessHandler;
import com.project.ticketseller.service.impl.UserServiceImpl;
import com.project.ticketseller.service.impl.VenueServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VenueController.class)
class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VenueServiceImpl venueService;


    @MockBean
    private CustomOAuth2UserService oAuth2UserService;
    @MockBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServiceImpl userService;


    @Test
    @WithMockCustomUser
    void listVenues() throws Exception {
        String url = "/venues";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void newVenueForm() throws Exception {
        String url = "/venues/new";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void saveVenue() throws Exception {

        Venue venue = Mockito.mock(Venue.class);

        String url = "/venues";

        ResultActions perform = mockMvc.perform(post(url).flashAttr("venue",venue).with(csrf()));

        perform.andExpect(status().is(302));
    }

    @Test
    @WithMockCustomUser
    void editVenueForm() throws Exception {
        String url = "/venues/edit/1";

        Venue venue = Mockito.mock(Venue.class);

        Mockito.when(venueService.getById(1l)).thenReturn(venue);

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void updateVenue() throws Exception {
        String url = "/venues/1";

        Venue venue = Mockito.mock(Venue.class);
        Venue existingVen = Mockito.mock(Venue.class);
        Mockito.when(venueService.getById(1l)).thenReturn(existingVen);
        Mockito.when(venue.getName()).thenReturn("MockName");
        Mockito.when(venue.getLocation()).thenReturn("MockLocation");
        Mockito.when(venue.getCapacity()).thenReturn(1000);

        ResultActions perform = mockMvc.perform(post(url).flashAttr("venue", venue).with(csrf()));
        perform.andDo(print()).andExpect((status().is(302)));
    }

    @Test
    @WithMockCustomUser
    void deleteVenue() throws Exception {
        String url = "/venues/1";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().is(302)));
    }
}
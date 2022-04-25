package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Category;
import com.project.ticketseller.entity.Event;
import com.project.ticketseller.entity.Venue;
import com.project.ticketseller.helpers.testOnly.WithMockCustomUser;
import com.project.ticketseller.repository.UserRepository;
import com.project.ticketseller.security.CustomOAuth2UserService;
import com.project.ticketseller.security.OAuth2LoginSuccessHandler;
import com.project.ticketseller.service.impl.CategoryServiceImpl;
import com.project.ticketseller.service.impl.EventServiceImpl;
import com.project.ticketseller.service.impl.UserServiceImpl;
import com.project.ticketseller.service.impl.VenueServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @MockBean
    private CategoryServiceImpl categoryService;

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
    void home() throws Exception {
        String url = "/";

        ResultActions perform = mockMvc.perform(get(url).param("keyword", "").with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void eventDetails() throws Exception {
        String url = "/event-details/1";

        Event event = Mockito.mock(Event.class);
        Venue venue = Mockito.mock(Venue.class);
        Mockito.when(event.getVenue()).thenReturn(venue);

        Mockito.when(eventService.getById(1l)).thenReturn(event);

        ResultActions perform = mockMvc.perform(get(url).flashAttr("event", event).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void listEvents() throws Exception {
        String url = "/events";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void newEventForm() throws Exception {
        String url = "/events/new";

        Event event = Mockito.mock(Event.class);
        List<Category> categories = new ArrayList<>();
        Category category = Mockito.mock(Category.class);
        categories.add(category);
        List<Venue> venues = new ArrayList<>();
        Venue venue = Mockito.mock(Venue.class);
        venues.add(venue);

        Map<String, Object> attrs = new HashMap<>();
        attrs.put("event", event);
        attrs.put("categories", categories);
        attrs.put("venues", venues);

        ResultActions perform = mockMvc.perform(get(url).flashAttrs(attrs).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void saveEvent() throws Exception {

        Event event = Mockito.mock(Event.class);
        MultipartFile file = Mockito.mock(MultipartFile.class);

        String url = "/events";

        ResultActions perform =
                mockMvc
                        .perform(multipart(url)
                                .file("coverPath", file.getBytes())
                                .flashAttr("event", event)
                                .with(csrf()));

        perform.andDo(print()).andExpect(status().is(302));
    }

    @Test
    @WithMockCustomUser
    void deleteEvent() throws Exception {
        String url = "/events/1";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().is(302)));
    }

    @Test
    @WithMockCustomUser
    void editEventForm() throws Exception {
        String url = "/events/edit/1";

        Event event = Mockito.mock(Event.class);
        Mockito.when(eventService.getById(1l)).thenReturn(event);

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void testExportToExcel() throws Exception {
        List<Event> events = new ArrayList<>();

        Category testCategory = new Category();
        testCategory.setId(1l);
        testCategory.setName("Test Category");

        Venue testVenue = new Venue();
        testVenue.setId(1l);
        testVenue.setName("Test Venue");
        testVenue.setCapacity(200);
        testVenue.setLocation("Test Location");

        Event testEvent = new Event();
        testEvent.setId(1l);
        testEvent.setTitle("Test");
        testEvent.setDescription("Test");
        testEvent.setCategory(testCategory);
        testEvent.setVenue(testVenue);
        testEvent.setTicketPrice(20f);
        testEvent.setDate(Date.valueOf(LocalDate.now()));
        testEvent.setCreationDate(Date.valueOf(LocalDate.now()));
        testEvent.setStartTime("15:00");

        events.add(testEvent);
        events.add(testEvent);
        events.add(testEvent);
        events.add(testEvent);


        Mockito.when(eventService.getAllEvents()).thenReturn(events);

        String url = "/events/export/excel";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        byte[] bytes = mvcResult.getResponse().getContentAsByteArray();
        Path path = Paths.get("events.xlsx");
        Files.write(path, bytes);

    }



}
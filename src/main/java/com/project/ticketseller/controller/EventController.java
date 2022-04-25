package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.helpers.EventExcelExporter;
import com.project.ticketseller.helpers.EventPDFExporter;
import com.project.ticketseller.service.CategoryService;
import com.project.ticketseller.service.EventService;
import com.project.ticketseller.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class EventController {

  private EventService eventService;
  private CategoryService categoryService;
  private VenueService venueService;

  @Autowired
  public EventController(
      EventService eventService, CategoryService categoryService, VenueService venueService) {
    this.eventService = eventService;
    this.categoryService = categoryService;
    this.venueService = venueService;
  }

  @GetMapping("/")
  public String listEvents(Model model, @Param("keyword") String keyword) {
    model.addAttribute("events", eventService.getAllEvents(keyword));
    model.addAttribute("keyword", keyword);
    return "home";
  }

  @GetMapping("/event-details/{id}")
  public String eventDetails(@PathVariable Long id, Model model) {
    model.addAttribute("event", eventService.getById(id));
    return "event-details";
  }

  @GetMapping("/events")
  public String events(Model model) {
    model.addAttribute("events", eventService.getAllEvents(""));
    return "events";
  }

  @GetMapping("/events/new")
  public String newEventForm(Model model) {
    model.addAttribute("event", new Event());
    model.addAttribute("venues", venueService.getAll());
    model.addAttribute("categories", categoryService.getAll());
    return "add-event";
  }

  @PostMapping("/events")
  public String saveEvent(
      @ModelAttribute("event") Event event, @RequestParam("coverPath") MultipartFile cover) {
    eventService.save(event, cover);
    return "redirect:/events";
  }

  @GetMapping("/events/{id}")
  public String deleteEvent(@PathVariable Long id) {
    eventService.deleteById(id);
    return "redirect:/events";
  }

  @GetMapping("/events/edit/{id}")
  public String editEventForm(@PathVariable Long id, Model model) {
    Event event = eventService.getById(id);
    model.addAttribute("event", event);
    model.addAttribute("venues", venueService.getAll());
    model.addAttribute("categories", categoryService.getAll());
    return "edit-event";
  }

  @PostMapping("/events/{id}")
  public String updateEvent(
      @PathVariable Long id,
      @RequestParam("coverPath") MultipartFile cover,
      @ModelAttribute("event") Event event) {
    eventService.update(id, event, cover);
    return "redirect:/events";
  }

  @GetMapping("/events/export/pdf")
  public void exportToPdf(HttpServletResponse response) throws IOException {
    response.setContentType("application/pdf");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=events.pdf";

    response.setHeader(headerKey, headerValue);

    List<Event> events = eventService.getAllEvents();

    EventPDFExporter exporter = new EventPDFExporter(events);
    exporter.export(response);
  }

  @GetMapping("/events/export/excel")
  public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=events.xlsx";

    response.setHeader(headerKey, headerValue);

    List<Event> events = eventService.getAllEvents();

    EventExcelExporter exporter = new EventExcelExporter(events);
    exporter.export(response);
  }
}

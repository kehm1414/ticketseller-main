package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Venue;
import com.project.ticketseller.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VenueController {

  private VenueService venueService;

  public VenueController(VenueService venueService) {
    this.venueService = venueService;
  }

  @GetMapping("/venues")
  public String listVenues(Model model) {
    model.addAttribute("venues", venueService.getAll());
    return "venues";
  }

  @GetMapping("venues/new")
  public String newVenueForm(Model model) {
    Venue venue = new Venue();
    model.addAttribute("venue", venue);
    return "add-venue";
  }

  @PostMapping("/venues")
  public String saveVenue(@ModelAttribute("venue") Venue venue) {
    venueService.save(venue);
    return "redirect:/venues";
  }

  @GetMapping("/venues/edit/{id}")
  public String editVenueForm(@PathVariable Long id, Model model) {
    Venue venue = venueService.getById(id);
    model.addAttribute("venue", venue);
    return "edit-venue";
  }

  @PostMapping("/venues/{id}")
  public String updateVenue(@PathVariable Long id, @ModelAttribute("venue") Venue venue) {
    Venue existingVenue = venueService.getById(id);
    existingVenue.setName(venue.getName());
    existingVenue.setLocation(venue.getLocation());
    existingVenue.setCapacity(venue.getCapacity());
    venueService.save(existingVenue);

    return "redirect:/venues";
  }

  @GetMapping("/venues/{id}")
  public String deleteVenue(@PathVariable Long id) {
    venueService.deleteById(id);
    return "redirect:/venues";
  }
}

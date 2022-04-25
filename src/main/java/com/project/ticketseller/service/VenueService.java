package com.project.ticketseller.service;

import com.project.ticketseller.entity.Venue;

import java.util.List;

public interface VenueService {

  Venue getById(Long id);

  List<Venue> getAll();

  void save(Venue venue);

  void deleteById(Long id);
}

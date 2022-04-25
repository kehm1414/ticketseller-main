package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.Venue;
import com.project.ticketseller.repository.VenueRepository;
import com.project.ticketseller.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

  VenueRepository venueRepository;

  @Autowired
  public VenueServiceImpl(VenueRepository venueRepository) {
    this.venueRepository = venueRepository;
  }

  @Override
  public Venue getById(Long id) {
    return venueRepository.getById(id);
  }

  @Override
  public List<Venue> getAll() {
    return venueRepository.findAll();
  }

  @Override
  public void save(Venue venue) {
    venueRepository.save(venue);
  }

  @Override
  public void deleteById(Long id) {
    venueRepository.deleteById(id);
  }
}

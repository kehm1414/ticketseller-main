package com.project.ticketseller.service;

import com.project.ticketseller.entity.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

  List<Event> getAllEvents(String keyword);
  List<Event> getAllEvents();

  Event getById(Long id);

  void save(Event event, MultipartFile cover);

  void update(Long id, Event event, MultipartFile cover);

  void deleteById(Long id);
}

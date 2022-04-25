package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.Event;
import com.project.ticketseller.helpers.UploadImage;
import com.project.ticketseller.repository.EventRepository;
import com.project.ticketseller.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

  EventRepository eventRepository;

  public static String uploadDirectory = System.getProperty("user.dir");
  public static File pwdDir = new File(uploadDirectory);
  public static File pwdDir2 =
      new File(pwdDir.getAbsoluteFile() + "/src/main/resources/static/images");

  @Autowired
  public EventServiceImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public List<Event> getAllEvents(String keyword) {
    if (!Objects.isNull(keyword)) {
      return eventRepository.findAll(keyword);
    } else {
      System.out.println("find all");
      return eventRepository.findAll();
    }
  }

  @Override
  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  @Override
  public Event getById(Long id) {
    return eventRepository.getById(id);
  }

  @Override
  public void save(Event event, MultipartFile cover) {
    event.setCreationDate(Date.valueOf(LocalDate.now()));
    event = eventRepository.save(event);
    // Image handling
    if (!cover.isEmpty()) saveImageCover(cover, event.getId());
  }

  @Override
  public void update(Long id, Event event, MultipartFile cover) {
    Event existingEvent = getById(id);
    existingEvent.setTitle(event.getTitle());
    existingEvent.setVenue(event.getVenue());
    existingEvent.setCategory(event.getCategory());
    existingEvent.setDate(event.getDate());
    existingEvent.setStartTime(event.getStartTime());
    existingEvent.setDescription(event.getDescription());
    existingEvent.setTicketPrice(event.getTicketPrice());
    existingEvent.setCreationDate(event.getCreationDate());
    save(existingEvent, cover);
  }

  @Override
  public void deleteById(Long id) {
    eventRepository.deleteById(id);
  }

  public void saveImageCover(MultipartFile cover, Long id) {
    try {
      UploadImage.upload(pwdDir2.getAbsolutePath(), id, cover);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

package com.project.ticketseller.repository;

import com.project.ticketseller.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(
      "SELECT e FROM Event e WHERE "
          + "CONCAT(e.title, e.venue.name, e.category.name)"
          + "LIKE %?1%")
  List<Event> findAll(String keyword);
}

package com.project.ticketseller.repository;

import com.project.ticketseller.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {}

package com.project.ticketseller.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date")
  private Date date;

  @Column(name = "start_time")
  private String startTime;

  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "venue_id")
  private Venue venue;

  @Column(name = "ticket_price")
  private Float ticketPrice;

  @Column(name = "creation_date")
  private Date creationDate;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Ticket> tickets;


  public Event() {}
}

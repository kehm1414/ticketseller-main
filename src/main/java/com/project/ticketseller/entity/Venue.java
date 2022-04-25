package com.project.ticketseller.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "venues")
public class Venue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private String location;

  @Column(name = "capacity")
  private int capacity;

  public Venue() {}
}

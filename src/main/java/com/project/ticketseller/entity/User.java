package com.project.ticketseller.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 45)
  private String username;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(name = "first_name", nullable = false, length = 20)
  private String firstName;

  @Column(name = "last_name", length = 20)
  private String lastName;

  @Column(name = "created_on", nullable = false)
  private Date createdOn;

  private boolean enabled;

  private String role;

  @Enumerated(EnumType.STRING)
  @Column(name = "auth_provider")
  private AuthenticationProvider authProvider;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Ticket> tickets;

  public User() {}
}

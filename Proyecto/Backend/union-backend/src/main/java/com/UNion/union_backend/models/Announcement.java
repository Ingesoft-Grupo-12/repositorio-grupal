package com.UNion.union_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "announcements")
public class Announcement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  private String content;

  @Column(name = "timestamp")
  private Instant timestamp;
}

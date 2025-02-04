package com.union.unionbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;

/**
 * Model class for reviews. Represents a review with its ID, course ID, reviewer ID, rating,
 * comment, and review date.
 */
@Data
@Entity
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private User teacher;

  private int rating;

  private String comment;

  @Column(name = "created_at")
  private Instant createdAt;
}

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
 * Model class for course requests. Represents a course request with its ID, course ID, student ID,
 * status, and request date.
 */
@Data
@Entity
@Table(name = "courserequests")
public class CourseRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private User student;

  private String status;

  @Column(name = "request_date")
  private Instant requestDate;
}

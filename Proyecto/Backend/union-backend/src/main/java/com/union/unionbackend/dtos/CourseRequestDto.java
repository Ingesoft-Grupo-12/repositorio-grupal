package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for course requests. Contains information about a course request,
 * including its ID, course ID, student ID, status, and request date.
 */
@Data
public class CourseRequestDto {

  private Long id;
  private Long courseId;
  private Long studentId;
  private String status;
  private Instant requestDate;
}
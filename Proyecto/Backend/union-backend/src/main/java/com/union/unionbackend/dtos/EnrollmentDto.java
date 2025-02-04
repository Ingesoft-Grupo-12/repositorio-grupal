package com.union.unionbackend.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for enrollments. Contains information about an enrollment, including
 * its ID, course ID, and student ID.
 */
@Data
public class EnrollmentDto {

  private Long id;
  private Long courseId;
  private Long studentId;
}
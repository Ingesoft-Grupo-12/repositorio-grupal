package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for enrollments. Contains information about an enrollment, including
 * its ID, course ID, and student ID.
 */
@Data
@AllArgsConstructor
public class EnrollmentDto {

  private Long id;
  private Long courseId;
  private String studentId;

}
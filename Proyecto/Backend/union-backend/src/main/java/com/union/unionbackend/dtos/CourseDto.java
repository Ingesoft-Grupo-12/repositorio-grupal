package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for courses. Contains information about a course, including its ID,
 * name, description, and teacher ID.
 */
@Data
@AllArgsConstructor
public class CourseDto {

  private Long id;
  private String name;
  private String description;
  private String teacherId;
}
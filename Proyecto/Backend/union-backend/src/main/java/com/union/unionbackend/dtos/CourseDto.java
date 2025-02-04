package com.union.unionbackend.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for courses. Contains information about a course, including its ID,
 * name, description, and teacher ID.
 */
@Data
public class CourseDto {

  private Long id;
  private String name;
  private String description;
  private Long teacherId;
}
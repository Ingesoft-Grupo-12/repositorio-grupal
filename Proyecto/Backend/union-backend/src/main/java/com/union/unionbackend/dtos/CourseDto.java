package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) for courses. Contains information about a course, including its ID,
 * name, description, teacher ID, and participants.
 */
@Data
@AllArgsConstructor
public class CourseDto {

  private Long id;
  private String name;
  private String description;
  private String teacherId;
  private List<UserDto> participants; // 🔹 Lista de participantes
}

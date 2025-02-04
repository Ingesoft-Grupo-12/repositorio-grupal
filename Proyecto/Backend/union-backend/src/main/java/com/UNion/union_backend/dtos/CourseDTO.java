package com.UNion.union_backend.dtos;

import lombok.Data;

@Data
public class CourseDTO {
  private Long id;
  private String name;
  private String description;
  private Long teacherId;
}

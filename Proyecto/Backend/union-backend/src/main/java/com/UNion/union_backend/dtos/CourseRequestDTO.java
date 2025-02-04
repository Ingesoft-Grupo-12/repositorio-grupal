package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class CourseRequestDTO {
  private Long id;
  private Long courseId;
  private Long studentId;
  private String status;
  private Instant requestDate;
}

package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDTO {
  private Long id;
  private Long courseId;
  private Long teacherId;
  private int rating;
  private String comment;
  private Instant createdAt;
}

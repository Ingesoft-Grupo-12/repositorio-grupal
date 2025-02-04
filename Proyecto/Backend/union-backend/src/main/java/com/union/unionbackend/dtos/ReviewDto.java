package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for responses. Contains information about a response, including its
 * ID, request ID, responder ID, and content.
 */
@Data
public class ReviewDto {

  private Long id;
  private Long courseId;
  private Long teacherId;
  private int rating;
  private String comment;
  private Instant createdAt;
}

package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class AnnouncementDTO {
  private Long id;
  private Long courseId;
  private String content;
  private Instant timestamp;
}

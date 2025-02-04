package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ChatDTO {
  private Long id;
  private String name;
  private Instant createdAt;
}
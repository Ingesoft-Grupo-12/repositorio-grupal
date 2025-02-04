package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for chat. Contains information about a chat, including its ID, name,
 * and creation timestamp.
 */
@Data
public class ChatDto {

  private Long id;
  private String name;
  private Instant createdAt;
}
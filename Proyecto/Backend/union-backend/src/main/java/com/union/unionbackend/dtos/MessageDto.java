package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for messages. Contains information about a message, including its ID,
 * chat ID, sender ID, content, and timestamp.
 */
@Data
public class MessageDto {

  private Long id;
  private Long chatId;
  private Long senderId;
  private String content;
  private Instant timestamp;
}
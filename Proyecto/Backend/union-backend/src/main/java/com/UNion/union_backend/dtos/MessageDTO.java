package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageDTO {
  private Long id;
  private Long senderId;
  private String content;
  private Instant timestamp;
  private Long chatId;
}

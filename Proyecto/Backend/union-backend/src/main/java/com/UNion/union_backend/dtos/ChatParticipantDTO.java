package com.UNion.union_backend.dtos;

import lombok.Data;

@Data
public class ChatParticipantDTO {
  private Long id;
  private Long chatId;
  private Long userId;
}

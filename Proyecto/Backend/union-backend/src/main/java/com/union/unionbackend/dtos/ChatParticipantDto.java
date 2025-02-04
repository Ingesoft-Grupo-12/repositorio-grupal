package com.union.unionbackend.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for chat participants. Contains information about a chat participant,
 * including their ID, the chat ID they belong to, and the user ID.
 */
@Data
public class ChatParticipantDto {

  private Long id;
  private Long chatId;
  private Long userId;
}
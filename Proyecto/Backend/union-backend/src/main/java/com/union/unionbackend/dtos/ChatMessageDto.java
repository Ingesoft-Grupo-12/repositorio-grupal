package com.union.unionbackend.dtos;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for chat messages.
 * Contains information about the sender, content, and timestamp of the message.
 */
public record ChatMessageDto(
    String sender,
    String content,
    LocalDateTime timestamp) {

}

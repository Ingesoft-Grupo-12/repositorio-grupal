package com.union.unionbackend.dtos;

import java.time.Instant;

/**
 * Data Transfer Object (DTO) for messages. Contains information about a message, including its ID,
 * chat ID, sender ID, content, and timestamp.
 */
public record MessageDto(Long senderId, String content, Instant timestamp) {

}
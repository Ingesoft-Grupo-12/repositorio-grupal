package com.union.unionbackend.dtos;

/**
 * Data Transfer Object (DTO) for messages. Contains information about a message, including its ID,
 * chat ID, sender ID, content, and timestamp.
 */
public record MessageDto(String name, String content, java.time.LocalDateTime timestamp) {

}
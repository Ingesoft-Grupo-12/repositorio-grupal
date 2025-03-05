package com.union.unionbackend.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public record ThreadDto(
    Long id,
    String title,
    Long courseId,
    LocalDateTime createdAt,
    Set<Long> messageIds
) {}

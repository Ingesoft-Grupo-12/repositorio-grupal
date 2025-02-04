package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for friend requests. Contains information about a friend request,
 * including its ID, sender ID, receiver ID, status, and request date.
 */
@Data
public class FriendRequestDto {

  private Long id;
  private Long senderId;
  private Long receiverId;
  private String status;
  private Instant requestDate;
}
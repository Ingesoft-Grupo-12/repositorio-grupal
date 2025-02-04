package com.UNion.union_backend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class FriendRequestDTO {
  private Long id;
  private Long senderId;
  private Long receiverId;
  private String status;
  private Instant requestDate;
}

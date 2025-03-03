package com.union.unionbackend.dtos;

import lombok.Data;

@Data
public class MessageRequest {

  private String content;
  private Long threadId; // Opcional para hilos
}
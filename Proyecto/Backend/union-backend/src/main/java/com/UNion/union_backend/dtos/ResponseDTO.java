package com.UNion.union_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
  private String status;
  private T data;
  private String message;
}

package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for responses. Contains information about a response, including its
 * ID, request ID, responder ID, and content.
 */
@Data
@AllArgsConstructor
public class ResponseDto<T> {

  private String status;
  private T data;
  private String message;
}

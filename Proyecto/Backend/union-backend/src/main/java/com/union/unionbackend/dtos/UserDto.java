package com.union.unionbackend.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for users. Contains information about a user, including their ID,
 * username, email, and role.
 */
@Data
public class UserDto {

  private Long id;
  private String username;
  private String email;
  private String role;
}
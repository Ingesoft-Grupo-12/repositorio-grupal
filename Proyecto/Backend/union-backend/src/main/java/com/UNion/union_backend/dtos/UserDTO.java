package com.UNion.union_backend.dtos;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String name;
  private String email;
  private String role;
  private String profilePicture;
}

package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String id;

  @NotBlank(message = "Username cannot be blank")
  private String username;

  @NotBlank(message = "Email cannot be blank")
  private String email;

  @NotBlank(message = "Role cannot be blank")
  private String role;

  @NotBlank(message = "Image cannot be blank")
  private String userimage;
}

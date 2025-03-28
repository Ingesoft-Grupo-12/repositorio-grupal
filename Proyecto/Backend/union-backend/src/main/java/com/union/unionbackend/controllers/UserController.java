package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.ResponseDto;
import com.union.unionbackend.dtos.UserDto;
import com.union.unionbackend.exceptions.UserServiceException;
import com.union.unionbackend.mapper.UserMapper;
import com.union.unionbackend.models.User;
import com.union.unionbackend.services.userService.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
    try {
      List<User> users = userService.getAllUsers();
      List<UserDto> userDtos = UserMapper.INSTANCE.usersToUserDtos(users);
      return ResponseEntity.ok(
              new ResponseDto<>(
                  "success", userDtos, "Users retrieved successfully"));
    } catch (UserServiceException e) {
      log.error("An error occurred while retrieving users", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ResponseDto<>("error", null,
                      "An unexpected error occurred: " + e.getMessage()));
    }
  }

  @GetMapping("/role")
  public ResponseEntity<ResponseDto<List<UserDto>>> getUsersByRole(@RequestParam String role) {
    try {
      List<User> users = userService.getUsersByRole(role);
      List<UserDto> userDtos = UserMapper.INSTANCE.usersToUserDtos(users);

      return ResponseEntity.ok(
              new ResponseDto<>(
                      "success", userDtos,
                      "Users retrieved successfully"));
    } catch (UserServiceException e) {
      log.error("An error occurred while retrieving users by role", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ResponseDto<>("error", null,
                      "An unexpected error occurred: " + e.getMessage()));
    }
  }
}

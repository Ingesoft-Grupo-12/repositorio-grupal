package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.ResponseDto;
import com.union.unionbackend.dtos.UserDto;
import com.union.unionbackend.exceptions.UserServiceException;
import com.union.unionbackend.mapper.UserMapper;
import com.union.unionbackend.models.User;
import com.union.unionbackend.services.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
    try {
      List<User> users = userService.getAllUsers();
      List<UserDto> userDtos = UserMapper.INSTANCE.usersToUserDtos(users);
      return ResponseEntity.ok(
          new ResponseDto<>("success", userDtos, "Users retrieved successfully"));
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
          new ResponseDto<>("success", userDtos, "Users retrieved successfully"));
    } catch (UserServiceException e) {
      log.error("An error occurred while retrieving users by role", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto<>("error", null,
              "An unexpected error occurred: " + e.getMessage()));
    }
  }

  @PostMapping("/sync")
  public User syncUser(@AuthenticationPrincipal Jwt jwt) {
    return userService.syncUser(
        jwt.getSubject(),       // auth0Id
        jwt.getClaim("email"),  // email
        jwt.getClaim("name")    // nombre completo
    );
  }

  /**
   * Obtiene un usuario por ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
    try {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(UserMapper.INSTANCE.userToDto(user));
    } catch (RuntimeException e) {
      log.error("Error fetching user by ID: {}", id, e);
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Crea un nuevo usuario.
   */
  @PostMapping()
  public ResponseEntity<?> createUser(@RequestBody User user) {
    if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
      return ResponseEntity.badRequest().body("Username cannot be null or empty");
    }
    return ResponseEntity.ok(userService.createUser(user));
  }

  /**
   * Actualiza la información de un usuario.
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
    Optional<User> updatedUser = userService.updateUser(id, UserMapper.INSTANCE.dtoToUser(userDto));
    return updatedUser.map(user -> ResponseEntity.ok(UserMapper.INSTANCE.userToDto(user)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Elimina un usuario.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    if (userService.deleteUser(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Cambia el rol de un usuario.
   */
  @PatchMapping("/{id}/role")
  public ResponseEntity<UserDto> changeUserRole(@PathVariable String id, @RequestParam String role) {
    Optional<User> updatedUser = userService.changeUserRole(id, role);
    return updatedUser.map(user -> ResponseEntity.ok(UserMapper.INSTANCE.userToDto(user)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Busca usuarios por nombre o correo.
   */
  @GetMapping("/search")
  public ResponseEntity<List<UserDto>> searchUsers(@RequestParam String query) {
    List<UserDto> users = userService.searchUsers(query).stream()
        .map(UserMapper.INSTANCE::userToDto).toList();
    return ResponseEntity.ok(users);
  }
}

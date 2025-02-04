package com.UNion.union_backend.controllers;

import com.UNion.union_backend.dtos.ResponseDTO;
import com.UNion.union_backend.models.User;
import com.UNion.union_backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  public ResponseEntity<ResponseDTO<List<User>>> getAllUsers() {
    try {
      return ResponseEntity.ok(new ResponseDTO<>("success", userService.getAllUsers(), "Se han obtenido los usuarios exitosamente"));
    } catch (Exception e) {
      log.error("Ocurrió un error al obtener los usuarios", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ResponseDTO<>("error", null, "Ocurrió un error inesperado: " + e.getMessage()));
    }
  }

  @GetMapping("/role")
  public ResponseEntity<ResponseDTO<List<User>>> getUsersByRole(@RequestParam String role) {
    try {
      return ResponseEntity.ok(new ResponseDTO<>("success", userService.getUsersByRole(role), "Se han obtenido los usuarios exitosamente"));
    } catch (Exception e) {
      log.error("Ocurrió un error al obtener los usuarios", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ResponseDTO<>("error", null, "Ocurrió un error inesperado: " + e.getMessage()));
    }
  }
}

package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.ResponseDto;
import com.union.unionbackend.models.User;
import com.union.unionbackend.services.UserService;
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
 * Controlador para gestionar operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  private final UserService userService;

  /**
   * Constructor de UserController.
   *
   * @param userService Servicio de usuarios.
   */
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Obtiene la lista de todos los usuarios.
   *
   * @return ResponseEntity con la lista de usuarios.
   */
  @GetMapping("/all")
  public ResponseEntity<ResponseDto<List<User>>> getAllUsers() {
    try {
      return ResponseEntity.ok(
          new ResponseDto<>(
              "success", userService.getAllUsers(),
              "Se han obtenido los usuarios exitosamente"));
    } catch (Exception e) {
      log.error("Ocurrió un error al obtener los usuarios", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto<>("error", null,
              "Ocurrió un error inesperado: " + e.getMessage()));
    }
  }

  /**
   * Obtiene la lista de usuarios por rol.
   *
   * @param role Rol de los usuarios a filtrar.
   * @return ResponseEntity con la lista de usuarios filtrada por rol.
   */
  @GetMapping("/role")
  public ResponseEntity<ResponseDto<List<User>>> getUsersByRole(@RequestParam String role) {
    try {
      return ResponseEntity.ok(
          new ResponseDto<>(
              "success", userService.getUsersByRole(role),
              "Se han obtenido los usuarios exitosamente"));
    } catch (Exception e) {
      log.error("Ocurrió un error al obtener los usuarios", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto<>("error", null,
              "Ocurrió un error inesperado: " + e.getMessage()));
    }
  }
}


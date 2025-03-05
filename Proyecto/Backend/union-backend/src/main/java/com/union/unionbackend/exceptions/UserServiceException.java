package com.union.unionbackend.exceptions;

/**
 * Excepción personalizada para errores en el servicio de usuarios.
 */
public class UserServiceException extends RuntimeException {

  public UserServiceException(String message) {
    super(message);
  }

  public UserServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

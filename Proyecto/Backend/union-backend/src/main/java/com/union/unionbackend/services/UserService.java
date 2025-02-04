package com.union.unionbackend.services;

import com.union.unionbackend.models.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Interfaz que define los métodos que se pueden realizar sobre la entidad User.
 */
@Service
public interface UserService {

  List<User> getAllUsers();

  List<User> getUsersByRole(String role);
}

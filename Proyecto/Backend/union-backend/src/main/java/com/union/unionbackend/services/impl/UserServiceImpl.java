package com.union.unionbackend.services.impl;

import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import com.union.unionbackend.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz UserService.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersByRole(String role) {
    return userRepository.findAllByRole(role);
  }
}

package com.union.unionbackend.services.userService;

import com.union.unionbackend.exceptions.UserServiceException;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
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
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      throw new UserServiceException("Error retrieving users", e);
    }
  }

  @Override
  public List<User> getUsersByRole(String role) {
    try {
      return userRepository.findAllByRole(role);
    } catch (Exception e) {
      throw new UserServiceException("Error retrieving users by role", e);
    }
  }

  @Override
  public Optional<User> getUserById(Long userId) {
    return Optional.empty();
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return Optional.empty();
  }

  @Override
  public User createUser(User user) {
    return null;
  }

  @Override
  public Optional<User> updateUser(Long userId, User user) {
    return Optional.empty();
  }

  @Override
  public boolean deleteUser(Long userId) {
    return false;
  }

  @Override
  public Optional<User> changeUserRole(Long userId, String newRole) {
    return Optional.empty();
  }

  @Override
  public List<User> searchUsers(String query) {
    return List.of();
  }

  @Override
  public boolean existsById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    return userRepository.existsById(id);
  }
}

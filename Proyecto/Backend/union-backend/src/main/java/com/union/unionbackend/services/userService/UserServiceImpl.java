package com.union.unionbackend.services.userService;

import com.union.unionbackend.models.Role;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
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
    return List.of();
  }

  @Override
  public List<User> getUsersByRole(String role) {
    return List.of();
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
  public boolean existsById(String id) {
    return false;
  }

  @Transactional
  @Override
  public User syncUser(String auth0Id, String email, String name) {
    return userRepository.findById(auth0Id)
        .map(existingUser -> {
          existingUser.setEmail(email);
          existingUser.setName(name);
          return userRepository.save(existingUser);
        })
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setId(auth0Id);
          newUser.setEmail(email);
          newUser.setName(name);
          newUser.setRole(Role.STUDENT); // Rol por defecto
          return userRepository.save(newUser);
        });
  }
}

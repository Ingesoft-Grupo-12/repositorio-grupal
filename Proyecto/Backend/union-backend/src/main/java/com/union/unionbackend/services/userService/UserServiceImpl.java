package com.union.unionbackend.services.userService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.union.unionbackend.dtos.AuthUserInfo;
import com.union.unionbackend.models.Role;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String apiUrl;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersByRole(String role) {
    return userRepository.findAll().stream()
        .filter(user -> user.getRole().name().equalsIgnoreCase(role))
        .toList();
  }

  @Override
  public User getUserById(String userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findAll().stream()
        .filter(user -> user.getEmail().equalsIgnoreCase(email))
        .findFirst();
  }

  @Override
  public User createUser(User user) {
    if (userRepository.existsById(user.getId())) {
      throw new RuntimeException("User already exists with ID: " + user.getId());
    }
    return userRepository.save(user);
  }

  @Override
  public Optional<User> updateUser(String userId, User updatedUser) {
    return userRepository.findById(userId).map(existingUser -> {
      existingUser.setUsername(updatedUser.getUsername());
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setRole(updatedUser.getRole());
      return userRepository.save(existingUser);
    });
  }

  @Override
  public boolean deleteUser(String userId) {
    if (!userRepository.existsById(userId)) {
      return false;
    }
    userRepository.deleteById(userId);
    return true;
  }

  @Override
  public Optional<User> changeUserRole(String userId, String newRole) {
    return userRepository.findById(userId).map(user -> {
      user.setRole(Role.valueOf(newRole.toUpperCase()));
      return userRepository.save(user);
    });
  }

  @Override
  public List<User> searchUsers(String query) {
    return userRepository.findAll().stream()
        .filter(user -> user.getUsername().toLowerCase().contains(query.toLowerCase()) ||
            user.getEmail().toLowerCase().contains(query.toLowerCase()))
        .toList();
  }

  @Override
  public boolean existsById(String id) {
    return userRepository.existsById(id);
  }

  @Transactional
  @Override
  public User syncUser(String auth0Id, String email, String name) {
    return userRepository.findById(auth0Id)
        .map(existingUser -> {
          existingUser.setEmail(email);
          existingUser.setUsername(name);
          return userRepository.save(existingUser);
        })
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setId(auth0Id);
          newUser.setEmail(email);
          newUser.setUsername(name);
          newUser.setRole(Role.STUDENT); // Rol por defecto
          return userRepository.save(newUser);
        });
  }

  @Override
  public User getOrCreateUser(Jwt jwt) {
    AuthUserInfo info = getWithBearerToken(jwt.getTokenValue());
    String auth0Id = jwt.getSubject();
    String email = info.getEmail();
    String name = info.getName();

    return userRepository.findById(auth0Id)
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setId(auth0Id);
          newUser.setEmail(email);
          newUser.setUsername(name);
          newUser.setRole(Role.STUDENT);
          return userRepository.save(newUser);
        });
  }

  @Override
  public AuthUserInfo getWithBearerToken(String token) {
    HttpURLConnection connection = null;
    try {
      URL url = new URL(apiUrl + "userinfo");
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + token);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, AuthUserInfo.class);
      } else {
        throw new RuntimeException("Error en la solicitud. Código: " + responseCode);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error al realizar la solicitud: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }
}

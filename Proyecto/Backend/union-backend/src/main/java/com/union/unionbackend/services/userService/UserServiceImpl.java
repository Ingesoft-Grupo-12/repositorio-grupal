package com.union.unionbackend.services.userService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.union.unionbackend.dtos.AuthUserInfo;
import com.union.unionbackend.models.Role;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz UserService.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String apiUrl;

  @Override
  public List<User> getAllUsers() {
    return List.of();
  }

  @Override
  public List<User> getUsersByRole(String role) {
    return List.of();
  }

  @Override
  public User getUserById(String userId) {
    return userRepository.findById(userId).orElseThrow();
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

  @Override
  public User getOrCreateUser(Jwt jwt) {
    AuthUserInfo info = getWithBearerToken(jwt.getTokenValue());
    String auth0Id = jwt.getSubject();  // Obtiene el "sub" del token (Auth0 User ID)
    String email = info.getEmail();
    String name = info.getName();

    return userRepository.findById(auth0Id)
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setId(auth0Id);
          newUser.setEmail(email);
          newUser.setName(name);
          newUser.setRole(Role.STUDENT); // Rol por defecto
          return userRepository.save(newUser);
        });
  }

  @Override
  public AuthUserInfo getWithBearerToken(String token) {
    HttpURLConnection connection = null;
    try {
      // Crear y configurar la conexión
      URL url = new URL(apiUrl + "userinfo");
      connection = (HttpURLConnection) url.openConnection();

      // Configurar método y headers
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + token);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");

      // Obtener respuesta
      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        // Leer respuesta
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));

        // Convertir JSON a objeto
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

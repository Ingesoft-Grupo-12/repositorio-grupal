package com.union.unionbackend.services.userService;

import com.union.unionbackend.dtos.AuthUserInfo;
import com.union.unionbackend.models.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Interfaz que define los métodos que se pueden realizar sobre la entidad User.
 */
@Service
public interface UserService {

  /**
   * Obtiene todos los usuarios registrados en el sistema.
   *
   * @return Lista de usuarios.
   */
  List<User> getAllUsers();

  /**
   * Obtiene una lista de usuarios según su rol (estudiante o profesor).
   *
   * @param role El rol de los usuarios a buscar.
   * @return Lista de usuarios con el rol especificado.
   */
  List<User> getUsersByRole(String role);

  /**
   * Busca un usuario por su ID.
   *
   * @param userId El ID del usuario a buscar.
   * @return Un Optional con el usuario si se encuentra.
   */
  User getUserById(String userId);

  /**
   * Busca un usuario por su correo electrónico.
   *
   * @param email El correo electrónico del usuario.
   * @return Un Optional con el usuario si se encuentra.
   */
  Optional<User> getUserByEmail(String email);

  /**
   * Crea un nuevo usuario en el sistema.
   *
   * @param user El objeto usuario con la información a registrar.
   * @return El usuario creado.
   */
  User createUser(User user);

  /**
   * Actualiza la información de un usuario.
   *
   * @param userId El ID del usuario a actualizar.
   * @param user   Los nuevos datos del usuario.
   * @return Un Optional con el usuario actualizado si la operación fue exitosa.
   */
  Optional<User> updateUser(String userId, User user); // 🔹 Se cambió Long -> String

  /**
   * Elimina un usuario por su ID.
   *
   * @param userId El ID del usuario a eliminar.
   * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
   */
  boolean deleteUser(String userId); // 🔹 Se cambió Long -> String

  /**
   * Cambia el rol de un usuario.
   *
   * @param userId  El ID del usuario a actualizar.
   * @param newRole El nuevo rol a asignar.
   * @return Un Optional con el usuario actualizado si la operación fue exitosa.
   */
  Optional<User> changeUserRole(String userId, String newRole); // 🔹 Se cambió Long -> String

  /**
   * Busca usuarios en la plataforma por nombre o correo.
   *
   * @param query Cadena de búsqueda (nombre o correo).
   * @return Lista de usuarios coincidentes.
   */
  List<User> searchUsers(String query);

  boolean existsById(String id);

  @Transactional
  User syncUser(String auth0Id, String email, String name);

  User getOrCreateUser(Jwt jwt);

  AuthUserInfo getWithBearerToken(String token);
}

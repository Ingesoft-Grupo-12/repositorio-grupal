package com.union.unionbackend.services.friendRequestService;

import com.union.unionbackend.models.FriendRequest;
import java.util.List;
import java.util.Optional;

public interface FriendRequestService {

  /**
   * Envía una solicitud de amistad a otro usuario.
   *
   * @param senderId   ID del usuario que envía la solicitud.
   * @param receiverId ID del usuario que recibe la solicitud.
   * @return Un Optional con la solicitud de amistad creada si la operación fue exitosa.
   */
  Optional<FriendRequest> sendFriendRequest(Long senderId, Long receiverId);

  /**
   * Acepta una solicitud de amistad.
   *
   * @param requestId ID de la solicitud de amistad.
   * @return Un Optional con la solicitud aceptada si la operación fue exitosa.
   */
  Optional<FriendRequest> acceptFriendRequest(Long requestId);

  /**
   * Rechaza una solicitud de amistad.
   *
   * @param requestId ID de la solicitud de amistad.
   * @return Un Optional con la solicitud rechazada si la operación fue exitosa.
   */
  Optional<FriendRequest> rejectFriendRequest(Long requestId);

  /**
   * Obtiene todas las solicitudes de amistad de un usuario.
   *
   * @param userId ID del usuario.
   * @return Lista de solicitudes de amistad asociadas al usuario.
   */
  List<FriendRequest> getFriendRequestsByUser(Long userId);

  /**
   * Obtiene todas las solicitudes de amistad pendientes de un usuario.
   *
   * @param userId ID del usuario.
   * @return Lista de solicitudes de amistad pendientes.
   */
  List<FriendRequest> getPendingFriendRequests(Long userId);

  /**
   * Elimina una solicitud de amistad por su ID.
   *
   * @param requestId ID de la solicitud de amistad.
   * @return true si la solicitud fue eliminada exitosamente, false en caso contrario.
   */
  boolean deleteFriendRequest(Long requestId);
}

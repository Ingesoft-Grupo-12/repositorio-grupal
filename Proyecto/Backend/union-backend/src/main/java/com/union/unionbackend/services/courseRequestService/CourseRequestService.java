package com.union.unionbackend.services.courseRequestService;

import com.union.unionbackend.models.CourseRequest;
import java.util.List;
import java.util.Optional;

public interface CourseRequestService {

  /**
   * Envía una invitación a un estudiante para unirse a un curso.
   *
   * @param email    Correo del estudiante.
   * @param courseId ID del curso.
   * @return true si la invitación fue enviada exitosamente, false en caso contrario.
   */
  boolean sendInvitation(String email, Long courseId);

  /**
   * Acepta una invitación a un curso.
   *
   * @param email    Correo del estudiante.
   * @param courseId ID del curso.
   * @return Un Optional con la solicitud de curso aceptada si la operación fue exitosa.
   */
  Optional<CourseRequest> acceptInvitation(String email, Long courseId);

  /**
   * Rechaza una invitación a un curso.
   *
   * @param email    Correo del estudiante.
   * @param courseId ID del curso.
   * @return Un Optional con la solicitud de curso rechazada si la operación fue exitosa.
   */
  Optional<CourseRequest> rejectInvitation(String email, Long courseId);

  /**
   * Obtiene todas las solicitudes pendientes de un curso.
   *
   * @param courseId ID del curso.
   * @return Lista de solicitudes pendientes.
   */
  List<CourseRequest> getPendingRequests(Long courseId);

  /**
   * Obtiene todas las solicitudes de un estudiante.
   *
   * @param studentId ID del estudiante.
   * @return Lista de solicitudes del estudiante.
   */
  List<CourseRequest> getRequestsByStudent(Long studentId);
}

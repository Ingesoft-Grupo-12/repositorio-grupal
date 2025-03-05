package com.union.unionbackend.services.enrollmentService;

import com.union.unionbackend.dtos.EnrollmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EnrollmentService {

  /**
   * Inscribe un estudiante en un curso.
   *
   * @param courseId  ID del curso.
   * @param studentId ID del estudiante.
   * @return Un Optional con la inscripción creada si la operación fue exitosa.
   */
  Optional<EnrollmentDto> enrollStudent(Long courseId, String studentId);

  /**
   * Obtiene una inscripción por su ID.
   *
   * @param enrollmentId ID de la inscripción.
   * @return Un Optional con la inscripción si se encuentra.
   */
  Optional<EnrollmentDto> getEnrollment(Long enrollmentId);

  /**
   * Desinscribe a un estudiante de un curso.
   *
   * @param enrollmentId ID de la inscripción.
   * @return true si la desinscripción fue exitosa, false en caso contrario.
   */
  boolean unenrollStudent(Long enrollmentId);

  /**
   * Obtiene todas las inscripciones de un curso.
   *
   * @param courseId ID del curso.
   * @return Lista de inscripciones asociadas al curso.
   */
  List<EnrollmentDto> getEnrollmentsByCourse(Long courseId);

  /**
   * Obtiene todos los cursos en los que un estudiante está inscrito.
   *
   * @param studentId ID del estudiante.
   * @return Lista de inscripciones asociadas al estudiante.
   */
  List<EnrollmentDto> getEnrollmentsByStudent(String studentId);

  /**
   * Verifica si un estudiante está inscrito en un curso.
   *
   * @param studentId ID del estudiante.
   * @param courseId  ID del curso.
   * @return true si el estudiante está inscrito en el curso, false en caso contrario.
   */
  boolean existsByStudentIdAndCourseId(String studentId, Long courseId);
}

package com.union.unionbackend.services.courseService;

import com.union.unionbackend.models.Course;
import java.io.File;
import java.util.List;
import java.util.Optional;

public interface CourseService {

  /**
   * Crea un nuevo curso.
   *
   * @param course Información del curso a crear.
   * @return El curso creado.
   */
  Course createCourse(Course course);

  /**
   * Actualiza un curso existente.
   *
   * @param courseId ID del curso a actualizar.
   * @param course   Nuevos datos del curso.
   * @return Un Optional con el curso actualizado si la operación fue exitosa.
   */
  Optional<Course> updateCourse(Long courseId, Course course);

  /**
   * Elimina un curso por su ID.
   *
   * @param id ID del curso a eliminar.
   * @return true si el curso fue eliminado exitosamente, false en caso contrario.
   */
  boolean deleteCourse(Long id);

  /**
   * Obtiene un curso por su ID.
   *
   * @param id ID del curso a buscar.
   * @return Un Course con el curso si se encuentra.
   */
  Course getCourse(Long id);

  /**
   * Obtiene todos los cursos disponibles en la plataforma.
   *
   * @return Lista de cursos.
   */
  List<Course> getAllCourses();

  /**
   * Obtiene todos los cursos de un profesor específico.
   *
   * @param teacherId ID del profesor.
   * @return Lista de cursos asociados al profesor.
   */
  List<Course> getCoursesByTeacher(Long teacherId);

  /**
   * Sube un archivo asociado a un curso.
   *
   * @param file Archivo a subir.
   * @return La URL o ruta del archivo almacenado.
   */
  String uploadFile(File file);

  /**
   * Verifica si un curso existe por su ID.
   *
   * @param id ID del curso a verificar.
   * @return true si el curso existe, false en caso contrario.
   */
  boolean existsById(Long id);

  Course validateCourseMembership(String userId, Long courseId);
}

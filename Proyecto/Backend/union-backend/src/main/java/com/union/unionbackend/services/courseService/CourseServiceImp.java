package com.union.unionbackend.services.courseService;

import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.CourseRepository;
import com.union.unionbackend.repositories.UserRepository;
import com.union.unionbackend.services.enrollmentService.EnrollmentService;
import java.io.File;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {

  private final CourseRepository courseRepository;
  private final EnrollmentService enrollmentService;
  private final UserRepository userRepository;

  @Override
  public Course createCourse(Course course) {
    return null;
  }

  @Override
  public Optional<Course> updateCourse(Long courseId, Course course) {
    return Optional.empty();
  }

  @Override
  public boolean deleteCourse(Long id) {
    return false;
  }

  @Override
  public Course getCourse(Long id) {
    return null;
  }

  @Override
  public List<Course> getAllCourses() {
    return List.of();
  }

  @Override
  public List<Course> getCoursesByTeacher(Long teacherId) {
    return List.of();
  }

  @Override
  public String uploadFile(File file) {
    return "";
  }

  @Override
  public boolean existsById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    return courseRepository.existsById(id);
  }

  @Override
  public Course validateCourseMembership(String userId, Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

    boolean isTeacher = course.getTeacher().getId().equals(userId);
    boolean isEnrolled = enrollmentService.existsByStudentIdAndCourseId(userId, courseId);

    if (!isTeacher && !isEnrolled) {
      throw new SecurityException("No tienes acceso a este chat");
    }

    return course;
  }

  @Override
  public User getAuthenticatedUser(String auth0Id) {
    return userRepository.findById(auth0Id)
        .orElseThrow(
            () -> new IllegalArgumentException("Usuario no encontrado con ID: " + auth0Id));
  }
}

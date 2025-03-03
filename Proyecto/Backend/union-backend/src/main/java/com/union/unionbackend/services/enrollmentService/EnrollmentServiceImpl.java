package com.union.unionbackend.services.enrollmentService;

import com.union.unionbackend.models.Enrollment;
import com.union.unionbackend.repositories.EnrollmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;

  public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
    this.enrollmentRepository = enrollmentRepository;
  }

  @Override
  public Optional<Enrollment> enrollStudent(Long courseId, Long studentId) {
    return Optional.empty();
  }

  @Override
  public Optional<Enrollment> getEnrollment(Long enrollmentId) {
    return Optional.empty();
  }

  @Override
  public boolean unenrollStudent(Long enrollmentId) {
    return false;
  }

  @Override
  public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
    return List.of();
  }

  @Override
  public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
    return List.of();
  }

  @Override
  public boolean existsByStudentIdAndCourseId(String studentId, Long courseId) {
    return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
  }
}

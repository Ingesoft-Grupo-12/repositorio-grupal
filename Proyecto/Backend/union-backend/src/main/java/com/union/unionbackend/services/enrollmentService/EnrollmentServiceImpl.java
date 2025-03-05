package com.union.unionbackend.services.enrollmentService;

import com.union.unionbackend.dtos.EnrollmentDto;
import com.union.unionbackend.mapper.EnrollmentMapper;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Enrollment;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.CourseRepository;
import com.union.unionbackend.repositories.EnrollmentRepository;
import com.union.unionbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  @Override
  public Optional<EnrollmentDto> enrollStudent(Long courseId, String studentId) {
    if (enrollmentRepository.existsByStudent_IdAndCourse_Id(studentId, courseId)) {
      return Optional.empty(); // El estudiante ya está inscrito
    }

    Optional<User> student = userRepository.findById(studentId);
    Optional<Course> course = courseRepository.findById(courseId);

    if (student.isPresent() && course.isPresent()) {
      Enrollment enrollment = Enrollment.builder()
          .student(student.get())
          .course(course.get())
          .enrolledAt(LocalDateTime.now())
          .build();
      return Optional.of(EnrollmentMapper.toDto(enrollmentRepository.save(enrollment)));
    }

    return Optional.empty(); // No se encontró el curso o el estudiante
  }

  @Override
  public Optional<EnrollmentDto> getEnrollment(Long enrollmentId) {
    return enrollmentRepository.findById(enrollmentId).map(EnrollmentMapper::toDto);
  }

  @Override
  public boolean unenrollStudent(Long enrollmentId) {
    if (enrollmentRepository.existsById(enrollmentId)) {
      enrollmentRepository.deleteById(enrollmentId);
      return true;
    }
    return false;
  }

  @Override
  public List<EnrollmentDto> getEnrollmentsByCourse(Long courseId) {
    return enrollmentRepository.findByCourse_Id(courseId)
        .stream().map(EnrollmentMapper::toDto).toList();
  }

  @Override
  public List<EnrollmentDto> getEnrollmentsByStudent(String studentId) {
    return enrollmentRepository.findByStudent_Id(studentId)
        .stream().map(EnrollmentMapper::toDto).toList();
  }

  @Override
  public boolean existsByStudentIdAndCourseId(String studentId, Long courseId) {
    return enrollmentRepository.existsByStudent_IdAndCourse_Id(studentId, courseId);
  }
}

package com.union.unionbackend.services.courseService;

import com.union.unionbackend.dtos.CourseDto;
import com.union.unionbackend.dtos.UserDto;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.repositories.CourseRepository;
import com.union.unionbackend.services.enrollmentService.EnrollmentService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {

  @Autowired
  EnrollmentService enrollmentService;
  private final CourseRepository courseRepository;

  @Override
  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  @Override
  public Optional<Course> updateCourse(Long courseId, Course courseDetails) {
    return courseRepository.findById(courseId).map(course -> {
      course.setCode(courseDetails.getCode());
      course.setName(courseDetails.getName());
      course.setDescription(courseDetails.getDescription());
      course.setTeacher(courseDetails.getTeacher());
      return courseRepository.save(course);
    });
  }

  @Override
  public boolean deleteCourse(Long id) {
    if (courseRepository.existsById(id)) {
      courseRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public Optional<CourseDto> getCourse(Long id) {
    return courseRepository.findById(id)
        .map(course -> {
          List<UserDto> participants = course.getEnrollments().stream()
              .map(enrollment -> new UserDto(
                  enrollment.getStudent().getId(),
                  enrollment.getStudent().getUsername(),
                  enrollment.getStudent().getEmail(),
                  enrollment.getStudent().getRole().name(), // 🔹 Convertir Enum a String
                  enrollment.getStudent().getUserimage()
              ))
              .toList();

          return new CourseDto(
              course.getId(),
              course.getName(),
              course.getDescription(),
              course.getTeacher().getId(),
              participants // 🔹 Se agregan los participantes
          );
        });
  }


  @Override
  public List<CourseDto> getAllCourses() {
    List<Course> courses = courseRepository.findAll();

    return courses.stream()
        .map(course -> {
          List<UserDto> participants = course.getEnrollments().stream()
              .map(enrollment -> new UserDto(
                  enrollment.getStudent().getId(),
                  enrollment.getStudent().getUsername(),
                  enrollment.getStudent().getEmail(),
                  enrollment.getStudent().getRole().toString(),
                  enrollment.getStudent().getUserimage()
              ))
              .toList();

          return new CourseDto(
              course.getId(),
              course.getName(),
              course.getDescription(),
              course.getTeacher().getId(),
              participants // 🔹 Se agrega la lista de participantes
          );
        })
        .toList();
  }




  @Override
  public Course validateCourseMembership(String userId, Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

    boolean isTeacher = course.getTeacher().getId().equals(userId);
    boolean isEnrolled = enrollmentService.existsByStudentIdAndCourseId((userId), courseId);

    if (!isTeacher && !isEnrolled) {
      throw new SecurityException("No tienes acceso a este curso");
    }

    return course;
  }

}

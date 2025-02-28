package com.union.unionbackend.services.courseService;

import com.union.unionbackend.models.Course;
import com.union.unionbackend.repositories.CourseRepository;
import java.io.File;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService {

  private final CourseRepository courseRepository;

  public CourseServiceImp(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }


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
}

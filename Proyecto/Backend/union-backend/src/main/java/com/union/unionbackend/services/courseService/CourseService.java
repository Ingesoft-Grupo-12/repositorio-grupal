package com.union.unionbackend.services.courseService;

import com.union.unionbackend.dtos.CourseDto;
import com.union.unionbackend.models.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

  Course createCourse(Course course);

  Optional<Course> updateCourse(Long courseId, Course course);

  boolean deleteCourse(Long id);

  Optional<CourseDto> getCourse(Long id);

  List<CourseDto> getAllCourses();

  Course validateCourseMembership(String userId, Long courseId);
}

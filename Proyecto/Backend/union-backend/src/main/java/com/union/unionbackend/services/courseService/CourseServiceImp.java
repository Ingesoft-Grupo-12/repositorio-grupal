package com.union.unionbackend.services.courseService;

import com.union.unionbackend.models.Course;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class CourseServiceImp implements CourseService {

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
    public Optional<Course> getCourse(Long id) {
        return Optional.empty();
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
}

package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

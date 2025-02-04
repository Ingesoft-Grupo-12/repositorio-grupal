package com.union.unionbackend.repositories;

import com.union.unionbackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Course entities. Provides CRUD operations for Course entities.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}

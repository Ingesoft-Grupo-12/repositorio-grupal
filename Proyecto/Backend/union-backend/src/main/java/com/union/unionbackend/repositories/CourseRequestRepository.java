package com.union.unionbackend.repositories;

import com.union.unionbackend.models.CourseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CourseRequest entities. Provides CRUD operations for CourseRequest
 * entities.
 */
@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Long> {

}

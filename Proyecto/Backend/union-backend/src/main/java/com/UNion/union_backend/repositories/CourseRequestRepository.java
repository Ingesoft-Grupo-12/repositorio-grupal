package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.CourseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRequestRepository extends JpaRepository<CourseRequest, Long> {
}

package com.union.unionbackend.repositories;

import com.union.unionbackend.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  boolean existsByStudent_IdAndCourse_Id(String studentId, Long courseId);

  List<Enrollment> findByCourse_Id(Long courseId);

  List<Enrollment> findByStudent_Id(String studentId);

  Optional<Enrollment> findByStudent_IdAndCourse_Id(String studentId, Long courseId);
}

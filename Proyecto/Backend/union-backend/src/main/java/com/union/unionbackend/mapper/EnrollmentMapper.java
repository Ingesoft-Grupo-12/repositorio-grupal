package com.union.unionbackend.mapper;

import com.union.unionbackend.dtos.EnrollmentDto;
import com.union.unionbackend.models.Enrollment;

public class EnrollmentMapper {

  public static EnrollmentDto toDto(Enrollment enrollment) {
    return new EnrollmentDto(
        enrollment.getId(),
        enrollment.getCourse().getId(),
        enrollment.getStudent().getId()
    );
  }
}

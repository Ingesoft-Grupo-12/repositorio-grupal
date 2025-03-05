package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.EnrollmentDto;
import com.union.unionbackend.services.enrollmentService.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  @PostMapping
  public ResponseEntity<EnrollmentDto> enrollStudent(@RequestParam Long courseId, @RequestParam String studentId) {
    Optional<EnrollmentDto> enrollment = enrollmentService.enrollStudent(courseId, studentId);
    return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EnrollmentDto> getEnrollment(@PathVariable Long id) {
    Optional<EnrollmentDto> enrollment = enrollmentService.getEnrollment(id);
    return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> unenrollStudent(@PathVariable Long id) {
    if (enrollmentService.unenrollStudent(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/course/{courseId}")
  public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByCourse(@PathVariable Long courseId) {
    return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
  }

  @GetMapping("/student/{studentId}")
  public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByStudent(@PathVariable String studentId) {
    return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
  }
}

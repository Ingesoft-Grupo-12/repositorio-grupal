  package com.union.unionbackend.controllers;

  import com.union.unionbackend.dtos.CourseDto;
  import com.union.unionbackend.models.Course;
  import com.union.unionbackend.services.courseService.CourseService;
  import java.util.List;
  import java.util.Optional;
  import lombok.RequiredArgsConstructor;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  @RestController
  @RequestMapping("/api/courses")
  @RequiredArgsConstructor
  public class CourseController {

    private final CourseService courseService;

    // 🔹 Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
      return ResponseEntity.ok(courseService.getAllCourses());
    }

    // 🔹 Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
      return courseService.getCourse(id)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
      return ResponseEntity.ok(courseService.createCourse(course));
    }

    // 🔹 Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
      return courseService.updateCourse(id, course)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar un curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
      boolean deleted = courseService.deleteCourse(id);
      return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
  }

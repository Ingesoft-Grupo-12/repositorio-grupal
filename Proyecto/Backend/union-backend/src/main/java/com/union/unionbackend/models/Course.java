package com.union.unionbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for responses. Contains information about a response, including its
 * ID, request ID, responder ID, and content.
 */
@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 100)
  @Column(nullable = false, unique = true)
  private String code; // Ej: "CS101"

  @NotBlank
  @Size(max = 100)
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Size(max = 500)
  @Column(nullable = false)
  private String description;

  // Profesor del curso
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private User teacher;

  // Estudiantes inscritos (relación muchos-a-muchos con tabla intermedia)
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Enrollment> enrollments = new HashSet<>();

  // Mensajes en el chat del curso
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Message> messages = new HashSet<>();

  // Hilos de discusión
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Thread> threads = new HashSet<>();
}

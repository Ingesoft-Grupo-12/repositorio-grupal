package com.union.unionbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for messages. Represents a message with its ID, chat ID, sender ID, content, and
 * timestamp.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @Column(name = "id", unique = true)
  private String id; // Coincide con el "sub" del token JWT de Auth0

  @NotBlank
  @Column(nullable = false, unique = true)
  private String email;

  @NotBlank
  @Column(nullable = false)
  private String username;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role; // TEACHER, STUDENT

  // Relación con cursos donde el usuario es profesor
  @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Course> taughtCourses = new HashSet<>();

  // Relación con cursos donde el usuario es estudiante (vía Enrollment)
  @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Enrollment> enrollments = new HashSet<>();

  // Mensajes enviados por el usuario
  @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Message> messages = new HashSet<>();

  @Column
  private String userimage;
}

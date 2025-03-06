package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.MessageDto;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Message;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.MessageRepository;
import com.union.unionbackend.services.courseService.CourseService;
import com.union.unionbackend.services.userService.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling messages related requests.
 */
@RestController
@RequestMapping("/api/courses/{courseId}/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageRepository messageRepository;
  private final CourseService courseService;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<MessageDto>> getMessages(
      @PathVariable Long courseId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "50") int size,
      @AuthenticationPrincipal Jwt jwt // Para validar permisos
  ) {
    // Validar que el usuario tiene acceso al curso
    User user = userService.getOrCreateUser(jwt);
    Course course = courseService.validateCourseMembership(user.getId(), courseId);

    // Obtener mensajes paginados
    Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
    Page<Message> messages = messageRepository.findByCourseId(courseId, pageable);

    // Convertir `Message` a `MessageDto`
    List<MessageDto> messageDtos = messages.getContent().stream()
        .map(message -> new MessageDto(
            message.getId(),
            message.getSender().getId(),
            message.getSender().getUsername(),
            message.getSender().getUserimage(),
            message.getContent(),
            message.getSentAt(),
            courseId
        ))
        .toList();

    return ResponseEntity.ok(messageDtos);
  }

}

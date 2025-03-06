package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.MessageDto;
import com.union.unionbackend.dtos.MessageRequest;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Message;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.MessageRepository;
import com.union.unionbackend.services.Message.MessageService;
import com.union.unionbackend.services.courseService.CourseService;
import com.union.unionbackend.services.userService.UserService;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CourseChatController {

  private final MessageRepository messageRepository;
  private final CourseService courseService;
  private final SimpMessagingTemplate messagingTemplate;
  private final UserService userService;
  private final MessageService messageService;

  @MessageMapping("/chat/{courseId}/send")
  public void handleChatMessage(
      @DestinationVariable Long courseId,
      @Payload MessageRequest messageRequest
  ) {
    // Obtener usuario autenticado desde el token JWT
    User sender = userService.getUserById(messageRequest.getSenderId());

    // Validar acceso al curso (profesor o estudiante inscrito)
    Course course = courseService.validateCourseMembership(sender.getId(), courseId);

    // Construir y guardar mensaje
    Message message = Message.builder()
        .content(messageRequest.getContent())
        .sender(sender)
        .course(course)
        .sentAt(LocalDateTime.now())
        .build();

    messageRepository.save(message);

    // Enviar mensaje a los suscriptores del curso
    messagingTemplate.convertAndSend(
        "/topic/courses/" + courseId + "/chat",
        new MessageDto(sender.getUsername(), message.getContent(), LocalDateTime.now())
    );
  }

  @PostMapping("/course/{courseId}/user/{userId}")
  public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto, @PathVariable Long courseId, @PathVariable Long userId) {
    return ResponseEntity.ok(messageService.createMessage(messageDto, courseId, userId));
  }

//  @GetMapping("/course/{courseId}")
//  public ResponseEntity<Page<MessageDto>> getMessagesByCourse(
//      @PathVariable Long courseId, Pageable pageable) {
//    return ResponseEntity.ok(messageService.getMessagesByCourse(courseId, pageable));
//  }

  @GetMapping("/{id}")
  public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) {
    return ResponseEntity.ok(messageService.getMessageById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MessageDto> updateMessage(@PathVariable Long id, @RequestBody MessageDto messageDto) {
    return ResponseEntity.ok(messageService.updateMessage(id, messageDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
    messageService.deleteMessage(id);
    return ResponseEntity.noContent().build();
  }
}
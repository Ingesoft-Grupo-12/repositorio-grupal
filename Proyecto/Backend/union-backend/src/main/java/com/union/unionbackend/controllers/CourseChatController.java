package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.MessageDto;
import com.union.unionbackend.dtos.MessageRequest;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Message;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.MessageRepository;
import com.union.unionbackend.services.courseService.CourseService;
import com.union.unionbackend.services.userService.UserService;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CourseChatController {

  private final MessageRepository messageRepository;
  private final CourseService courseService;
  private final SimpMessagingTemplate messagingTemplate;
  private final UserService userService;

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
        new MessageDto(sender.getName(), message.getContent(), Instant.now())
    );
  }
}
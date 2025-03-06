package com.union.unionbackend.services.Message;

import com.union.unionbackend.dtos.MessageDto;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Message;
import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.CourseRepository;
import com.union.unionbackend.repositories.MessageRepository;
import com.union.unionbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final CourseRepository courseRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public MessageDto createMessage(MessageDto messageDto, Long courseId, Long senderId) {
    Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    User sender = userRepository.findById(String.valueOf(senderId)).orElseThrow(() -> new IllegalArgumentException("Sender not found"));

    Message message = Message.builder()
        .content(messageDto.content())
        .sentAt(LocalDateTime.now())
        .sender(sender)
        .course(course)
        .build();

    message = messageRepository.save(message);
    return new MessageDto(message.getId(), message.getSender().getId(), message.getSender().getUsername(), message.getSender().getUserimage(), message.getContent(), message.getSentAt(), message.getCourse().getId());
  }


  @Override
  public Page<MessageDto> getMessagesByCourse(Long courseId, Pageable pageable) {
    return messageRepository.findByCourseId(courseId, pageable)
        .map(msg -> new MessageDto(msg.getId(), msg.getSender().getId(), msg.getSender().getUsername(), msg.getSender().getUserimage(), msg.getContent(), msg.getSentAt(), msg.getCourse().getId()));
  }

  @Override
  public MessageDto getMessageById(Long id) {
    Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Message not found"));
    return new MessageDto(message.getId(), message.getSender().getId(), message.getSender().getUsername(), message.getSender().getUserimage(), message.getContent(), message.getSentAt(), message.getCourse().getId());
  }

  @Override
  @Transactional
  public MessageDto updateMessage(Long id, MessageDto messageDto) {
    Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Message not found"));
    message.setContent(messageDto.content());
    message = messageRepository.save(message);
    return new MessageDto(message.getId(), message.getSender().getId(), message.getSender().getUsername(), message.getSender().getUserimage(), message.getContent(), message.getSentAt(), message.getCourse().getId());
  }

  @Override
  @Transactional
  public void deleteMessage(Long id) {
    messageRepository.deleteById(id);
  }
}
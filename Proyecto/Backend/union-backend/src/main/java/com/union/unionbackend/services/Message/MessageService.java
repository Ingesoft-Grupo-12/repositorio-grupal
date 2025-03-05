package com.union.unionbackend.services.Message;

import com.union.unionbackend.dtos.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
  MessageDto createMessage(MessageDto messageDto, Long courseId, Long senderId);
  Page<MessageDto> getMessagesByCourse(Long courseId, Pageable pageable);
  MessageDto getMessageById(Long id);
  MessageDto updateMessage(Long id, MessageDto messageDto);
  void deleteMessage(Long id);
}
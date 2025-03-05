package com.union.unionbackend.services.Message;

import com.union.unionbackend.dtos.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

  @Override
  public MessageDto processMessage(MessageDto message, Long courseId) {
    return null;
  }
}

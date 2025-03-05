package com.union.unionbackend.services.Message;

import com.union.unionbackend.dtos.MessageDto;

public interface MessageService {

  MessageDto processMessage(MessageDto message, Long courseId);
}

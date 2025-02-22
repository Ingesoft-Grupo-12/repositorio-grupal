package com.union.unionbackend.services.chatService;

import com.union.unionbackend.models.Chat;
import com.union.unionbackend.repositories.ChatRepository;
import com.union.unionbackend.services.userService.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;
  private final UserService userService;

  public ChatServiceImpl(ChatRepository chatRepository, UserService userService) {
    this.chatRepository = chatRepository;
    this.userService = userService;
  }

  @Override
  @Transactional
  public Chat createChat(Chat chat) {
    return chatRepository.save(chat);
  }

  @Override
  public Chat getChat(Long id) {
    return chatRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Chat not found with id " + id));
  }

  @Override
  @Transactional
  public Chat updateChat(Long id, Chat chat) {
    return chatRepository.findById(id)
        .map(existingChat -> {
          existingChat.setName(chat.getName());
          return chatRepository.save(existingChat);
        })
        .orElseThrow(() -> new EntityNotFoundException("Chat not found with id " + id));
  }

  @Override
  @Transactional
  public boolean deleteChat(Long id) {
    try {
      if (!chatRepository.existsById(id)) {
        log.error("Chat not found with id {}", id);
        return false;
      }
      chatRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      log.error("Error deleting Chat with id {}: {}", id, e.getMessage());
      return false;
    }
  }

  @Override
  public List<Chat> getAllChats() {
    return chatRepository.findAll();
  }

  @Override
  public List<Chat> getChatsByUser(Long userId) {
    if (!userService.existsById(userId)) {
      throw new EntityNotFoundException("User not found with id " + userId);
    }
    return new ArrayList<>();
  }

  @Override
  @Transactional
  public boolean addUserToChat(Long chatId, Long userId) {
    if (!chatRepository.existsById(chatId)) {
      throw new EntityNotFoundException("Chat not found with id " + chatId);
    }
    if (!userService.existsById(userId)) {
      throw new EntityNotFoundException("User not found with id " + userId);
    }
    return true;
  }

  @Override
  @Transactional
  public boolean removeUserFromChat(Long chatId, Long userId) {
    if (!chatRepository.existsById(chatId)) {
      throw new EntityNotFoundException("Chat not found with id " + chatId);
    }
    if (!userService.existsById(userId)) {
      throw new EntityNotFoundException("User not found with id " + userId);
    }
    return true;
  }

  @Override
  public boolean existsById(Long id) {
    if(id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    return chatRepository.existsById(id);
  }
}
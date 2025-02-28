package com.union.unionbackend.services.chatParticipantService;

import com.union.unionbackend.models.ChatParticipant;
import com.union.unionbackend.repositories.ChatParticipantRepository;
import com.union.unionbackend.services.chatService.ChatService;
import com.union.unionbackend.services.userService.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatParticipantServiceImp implements ChatParticipantService {

  private final ChatParticipantRepository chatParticipantRepository;
  private final UserService userService;
  private final ChatService chatService;

  public ChatParticipantServiceImp(ChatParticipantRepository chatParticipantRepository,
      UserService userService, ChatService chatService) {
    this.chatParticipantRepository = chatParticipantRepository;
    this.userService = userService;
    this.chatService = chatService;
  }

  @Override
  @Transactional
  public ChatParticipant createChatParticipant(ChatParticipant chatParticipant) {
    if (!userService.existsById(chatParticipant.getUser().getId())) {
      throw new EntityNotFoundException(
          "User not found with id " + chatParticipant.getUser().getId());
    }
    if (!chatService.existsById(chatParticipant.getChat().getId())) {
      throw new EntityNotFoundException(
          "Chat not found with id " + chatParticipant.getChat().getId());
    }
    return chatParticipantRepository.save(chatParticipant);
  }

  @Override
  public ChatParticipant getChatParticipant(Long id) {
    return chatParticipantRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("ChatParticipant not found with id " + id));
  }

  @Override
  @Transactional
  public ChatParticipant updateChatParticipant(Long id, ChatParticipant chatParticipant) {
    return chatParticipantRepository.findById(id)
        .map(existingParticipant -> {
          existingParticipant.setChat(chatParticipant.getChat());
          existingParticipant.setUser(chatParticipant.getUser());
          return chatParticipantRepository.save(existingParticipant);
        })
        .orElseThrow(() -> new EntityNotFoundException("ChatParticipant not found with id " + id));
  }

  @Override
  @Transactional
  public boolean deleteChatParticipant(Long id) {
    try {
      if (!chatParticipantRepository.existsById(id)) {
        log.error("ChatParticipant not found with id {}", id);
        return false;
      }
      chatParticipantRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      log.error("Error deleting ChatParticipant with id {}: {}", id, e.getMessage());
      return false;
    }
  }

  @Override
  public List<ChatParticipant> getParticipantsByChat(Long chatId) {
    if (!chatService.existsById(chatId)) {
      throw new EntityNotFoundException("Chat not found with id " + chatId);
    }
    return chatParticipantRepository.findAllByChatId(chatId);
  }
}
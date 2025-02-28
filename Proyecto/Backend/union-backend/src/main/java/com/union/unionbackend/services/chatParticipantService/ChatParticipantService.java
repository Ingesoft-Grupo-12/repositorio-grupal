package com.union.unionbackend.services.chatParticipantService;

import com.union.unionbackend.models.ChatParticipant;
import java.util.List;

public interface ChatParticipantService {

  /**
   * Creates a new chat participant.
   *
   * @param chatParticipant Information of the participant to register.
   * @return The created participant.
   */
  ChatParticipant createChatParticipant(ChatParticipant chatParticipant);

  /**
   * Retrieves a chat participant by their ID.
   *
   * @param id ID of the participant.
   * @return The participant with the specified ID.
   */
  ChatParticipant getChatParticipant(Long id);

  /**
   * Updates the information of a chat participant.
   *
   * @param id              ID of the participant to update.
   * @param chatParticipant New data for the participant.
   * @return The updated participant.
   */
  ChatParticipant updateChatParticipant(Long id, ChatParticipant chatParticipant);

  /**
   * Deletes a chat participant by their ID.
   *
   * @param id ID of the participant.
   * @return true if the participant was successfully deleted, false otherwise.
   */
  boolean deleteChatParticipant(Long id);

  /**
   * Retrieves all participants of a chat.
   *
   * @param chatId ID of the chat.
   * @return A list of participants in the chat.
   */
  List<ChatParticipant> getParticipantsByChat(Long chatId);
}

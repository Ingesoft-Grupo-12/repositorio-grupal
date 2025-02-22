package com.union.unionbackend.services.chatService;

import com.union.unionbackend.models.Chat;
import java.util.List;

public interface ChatService {

  /**
   * Creates a new chat.
   *
   * @param chat Information of the chat to create.
   * @return The created chat.
   */
  Chat createChat(Chat chat);

  /**
   * Retrieves a chat by its ID.
   *
   * @param id ID of the chat.
   * @return The chat with the specified ID.
   */
  Chat getChat(Long id);

  /**
   * Updates an existing chat.
   *
   * @param id   ID of the chat to update.
   * @param chat New data for the chat.
   * @return The updated chat.
   */
  Chat updateChat(Long id, Chat chat);

  /**
   * Deletes a chat by its ID.
   *
   * @param id ID of the chat to delete.
   * @return true if the chat was successfully deleted, false otherwise.
   */
  boolean deleteChat(Long id);

  /**
   * Retrieves all chats in the system.
   *
   * @return List of chats.
   */
  List<Chat> getAllChats();

  /**
   * Retrieves all chats in which a user participates.
   *
   * @param userId ID of the user.
   * @return List of chats associated with the user.
   */
  List<Chat> getChatsByUser(Long userId);

  /**
   * Adds a user to a chat.
   *
   * @param chatId ID of the chat.
   * @param userId ID of the user.
   * @return true if the user was successfully added, false otherwise.
   */
  boolean addUserToChat(Long chatId, Long userId);

  /**
   * Removes a user from a chat.
   *
   * @param chatId ID of the chat.
   * @param userId ID of the user.
   * @return true if the user was successfully removed, false otherwise.
   */
  boolean removeUserFromChat(Long chatId, Long userId);

  /**
   * Checks if a chat exists by its ID.
   *
   * @param id ID of the chat.
   * @return true if the chat exists, false otherwise.
   */
  boolean existsById(Long id);
}

package com.union.unionbackend.services.chatService;

import com.union.unionbackend.models.Chat;
import java.util.List;
import java.util.Optional;

public interface ChatService {

    /**
     * Crea un nuevo chat.
     * @param chat Información del chat a crear.
     * @return El chat creado.
     */
    Chat createChat(Chat chat);

    /**
     * Obtiene un chat por su ID.
     * @param id ID del chat.
     * @return Un Optional con el chat si se encuentra.
     */
    Optional<Chat> getChat(Long id);

    /**
     * Actualiza un chat existente.
     * @param id ID del chat a actualizar.
     * @param chat Nuevos datos del chat.
     * @return Un Optional con el chat actualizado si la operación fue exitosa.
     */
    Optional<Chat> updateChat(Long id, Chat chat);

    /**
     * Elimina un chat por su ID.
     * @param id ID del chat a eliminar.
     * @return true si el chat fue eliminado exitosamente, false en caso contrario.
     */
    boolean deleteChat(Long id);

    /**
     * Obtiene todos los chats en el sistema.
     * @return Lista de chats.
     */
    List<Chat> getAllChats();

    /**
     * Obtiene todos los chats en los que un usuario participa.
     * @param userId ID del usuario.
     * @return Lista de chats asociados al usuario.
     */
    List<Chat> getChatsByUser(Long userId);

    /**
     * Agrega un usuario a un chat.
     * @param chatId ID del chat.
     * @param userId ID del usuario.
     * @return true si el usuario fue agregado exitosamente, false en caso contrario.
     */
    boolean addUserToChat(Long chatId, Long userId);

    /**
     * Elimina un usuario de un chat.
     * @param chatId ID del chat.
     * @param userId ID del usuario.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     */
    boolean removeUserFromChat(Long chatId, Long userId);
}

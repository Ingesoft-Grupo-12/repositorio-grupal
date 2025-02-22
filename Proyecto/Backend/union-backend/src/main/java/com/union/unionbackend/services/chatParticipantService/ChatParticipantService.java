package com.union.unionbackend.services.chatParticipantService;

import com.union.unionbackend.models.ChatParticipant;
import java.util.List;
import java.util.Optional;

public interface ChatParticipantService {

    /**
     * Crea un nuevo participante en un chat.
     * @param chatParticipant Información del participante a registrar.
     * @return El participante creado.
     */
    ChatParticipant createChatParticipant(ChatParticipant chatParticipant);

    /**
     * Obtiene un participante de un chat por su ID.
     * @param id ID del participante.
     * @return Un Optional con el participante si se encuentra.
     */
    Optional<ChatParticipant> getChatParticipant(Long id);

    /**
     * Actualiza la información de un participante en un chat.
     * @param id ID del participante a actualizar.
     * @param chatParticipant Nuevos datos del participante.
     * @return Un Optional con el participante actualizado si la operación fue exitosa.
     */
    Optional<ChatParticipant> updateChatParticipant(Long id, ChatParticipant chatParticipant);

    /**
     * Elimina un participante de un chat por su ID.
     * @param id ID del participante.
     * @return true si el participante fue eliminado exitosamente, false en caso contrario.
     */
    boolean deleteChatParticipant(Long id);

    /**
     * Obtiene todos los participantes de un chat.
     * @param chatId ID del chat.
     * @return Lista de participantes en el chat.
     */
    List<ChatParticipant> getParticipantsByChat(Long chatId);
}

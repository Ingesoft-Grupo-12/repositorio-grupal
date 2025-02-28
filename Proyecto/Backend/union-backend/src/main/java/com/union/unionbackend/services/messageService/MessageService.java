package com.union.unionbackend.services.messageService;

import com.union.unionbackend.models.Message;
import java.util.List;
import java.util.Optional;

public interface MessageService {

  /**
   * Envía un nuevo mensaje en un chat.
   *
   * @param message Información del mensaje a enviar.
   * @return El mensaje enviado.
   */
  Message sendMessage(Message message);

  /**
   * Obtiene un mensaje por su ID.
   *
   * @param messageId ID del mensaje.
   * @return Un Optional con el mensaje si se encuentra.
   */
  Optional<Message> getMessageById(Long messageId);

  /**
   * Obtiene todos los mensajes de un chat específico.
   *
   * @param chatId ID del chat.
   * @return Lista de mensajes en el chat.
   */
  List<Message> getMessagesByChat(Long chatId);

  /**
   * Obtiene todos los mensajes enviados por un usuario.
   *
   * @param userId ID del usuario.
   * @return Lista de mensajes enviados por el usuario.
   */
  List<Message> getMessagesByUser(Long userId);

  /**
   * Actualiza el contenido de un mensaje.
   *
   * @param messageId  ID del mensaje.
   * @param newContent Nuevo contenido del mensaje.
   * @return Un Optional con el mensaje actualizado si la operación fue exitosa.
   */
  Optional<Message> updateMessage(Long messageId, String newContent);

  /**
   * Elimina un mensaje por su ID.
   *
   * @param messageId ID del mensaje a eliminar.
   * @return true si el mensaje fue eliminado exitosamente, false en caso contrario.
   */
  boolean deleteMessage(Long messageId);
}

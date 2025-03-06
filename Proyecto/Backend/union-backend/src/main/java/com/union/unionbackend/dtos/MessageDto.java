package com.union.unionbackend.dtos;
import java.time.LocalDateTime;

public record MessageDto(
    Long id,            // 🔹 ID del mensaje
    String senderId,    // 🔹 ID del remitente
    String senderName,  // 🔹 Nombre del remitente
    String senderImage, // 🔹 Imagen del remitente
    String content,     // 🔹 Contenido del mensaje
    LocalDateTime timestamp, // 🔹 Hora de envío
    Long courseId       // 🔹 ID del curso
) {}

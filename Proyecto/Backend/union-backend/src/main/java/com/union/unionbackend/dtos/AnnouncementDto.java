package com.union.unionbackend.dtos;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) para los anuncios. Contiene información sobre los anuncios, incluyendo
 * su identificador, el identificador del curso al que pertenece, el contenido y la marca de
 * tiempo.
 */
@Data
public class AnnouncementDto {

  private Long id;
  private Long courseId;
  private String content;
  private Instant timestamp;
}
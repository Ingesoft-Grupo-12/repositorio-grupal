package com.union.unionbackend.services.announcementService;

import com.union.unionbackend.models.Announcement;
import java.util.List;
import java.util.Optional;

public interface AnnouncementService {

    /**
     * Crea un nuevo anuncio.
     * @param announcement Información del anuncio a crear.
     * @return El anuncio creado.
     */
    Announcement createAnnouncement(Announcement announcement);

    /**
     * Obtiene un anuncio por su ID.
     * @param id ID del anuncio.
     * @return Un Optional con el anuncio si se encuentra.
     */
    Optional<Announcement> getAnnouncement(Long id);

    /**
     * Actualiza un anuncio existente.
     * @param id ID del anuncio a actualizar.
     * @param announcement Nuevos datos del anuncio.
     * @return Un Optional con el anuncio actualizado si la operación fue exitosa.
     */
    Optional<Announcement> updateAnnouncement(Long id, Announcement announcement);

    /**
     * Elimina un anuncio por su ID.
     * @param id ID del anuncio a eliminar.
     * @return true si el anuncio fue eliminado exitosamente, false en caso contrario.
     */
    boolean deleteAnnouncement(Long id);

    /**
     * Obtiene todos los anuncios de un curso específico.
     * @param courseId ID del curso.
     * @return Lista de anuncios asociados al curso.
     */
    List<Announcement> getAnnouncementsByCourse(Long courseId);
}

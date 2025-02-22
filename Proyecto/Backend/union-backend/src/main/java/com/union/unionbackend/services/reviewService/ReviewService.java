package com.union.unionbackend.services.reviewService;

import com.union.unionbackend.models.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {

    /**
     * Crea una nueva reseña para un curso y profesor.
     * @param review Información de la reseña.
     * @return La reseña creada.
     */
    Review createReview(Review review);

    /**
     * Obtiene una reseña por su ID.
     * @param reviewId ID de la reseña.
     * @return Un Optional con la reseña si se encuentra.
     */
    Optional<Review> getReviewById(Long reviewId);

    /**
     * Obtiene todas las reseñas de un curso específico.
     * @param courseId ID del curso.
     * @return Lista de reseñas asociadas al curso.
     */
    List<Review> getReviewsByCourse(Long courseId);

    /**
     * Obtiene todas las reseñas de un profesor específico.
     * @param teacherId ID del profesor.
     * @return Lista de reseñas asociadas al profesor.
     */
    List<Review> getReviewsByTeacher(Long teacherId);

    /**
     * Actualiza una reseña existente.
     * @param reviewId ID de la reseña a actualizar.
     * @param newRating Nueva calificación.
     * @param newComment Nuevo comentario.
     * @return Un Optional con la reseña actualizada si la operación fue exitosa.
     */
    Optional<Review> updateReview(Long reviewId, int newRating, String newComment);

    /**
     * Elimina una reseña por su ID.
     * @param reviewId ID de la reseña.
     * @return true si la reseña fue eliminada exitosamente, false en caso contrario.
     */
    boolean deleteReview(Long reviewId);
}

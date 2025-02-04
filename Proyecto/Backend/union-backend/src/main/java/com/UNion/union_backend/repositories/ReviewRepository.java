package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

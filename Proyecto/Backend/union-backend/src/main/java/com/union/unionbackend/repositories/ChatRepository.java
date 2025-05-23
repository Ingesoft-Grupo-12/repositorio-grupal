package com.union.unionbackend.repositories;

import com.union.unionbackend.models.Chat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Review entities. Provides CRUD operations for Review entities.
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}

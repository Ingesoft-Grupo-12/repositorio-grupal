package com.union.unionbackend.repositories;

import com.union.unionbackend.models.ChatParticipant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Review entities. Provides CRUD operations for Review entities.
 */
@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {

  List<ChatParticipant> findAllByChatId(Long chatId);
}

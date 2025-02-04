package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
}

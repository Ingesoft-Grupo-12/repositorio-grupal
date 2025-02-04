package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}

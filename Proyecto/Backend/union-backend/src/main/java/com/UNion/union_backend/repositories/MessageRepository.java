package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

package com.union.unionbackend.repositories;

import com.union.unionbackend.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Message entities. Provides CRUD operations for Message entities.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  Page<Message> findByCourseId(Long courseId, Pageable pageable);
}

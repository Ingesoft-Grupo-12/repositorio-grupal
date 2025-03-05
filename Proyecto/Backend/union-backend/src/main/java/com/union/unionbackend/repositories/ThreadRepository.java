package com.union.unionbackend.repositories;

import com.union.unionbackend.models.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
  List<Thread> findByCourseId(Long courseId);
}

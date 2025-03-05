package com.union.unionbackend.services.threadService;

import com.union.unionbackend.dtos.ThreadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThreadService {
  Page<ThreadDto> getAllThreads(Pageable pageable);
  Optional<ThreadDto> getThreadById(Long id);
  ThreadDto createThread(ThreadDto threadDto);
  Optional<ThreadDto> updateThread(Long id, ThreadDto threadDto);
  boolean deleteThread(Long id);
}

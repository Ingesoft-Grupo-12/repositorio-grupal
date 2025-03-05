package com.union.unionbackend.services.threadService;

import com.union.unionbackend.dtos.ThreadDto;
import com.union.unionbackend.mapper.ThreadMapper;
import com.union.unionbackend.models.Thread;
import com.union.unionbackend.repositories.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

  private final ThreadRepository threadRepository;

  @Override
  public Page<ThreadDto> getAllThreads(Pageable pageable) {
    return threadRepository.findAll(pageable).map(ThreadMapper::toDto);
  }

  @Override
  public Optional<ThreadDto> getThreadById(Long id) {
    return threadRepository.findById(id).map(ThreadMapper::toDto);
  }

  @Override
  public ThreadDto createThread(ThreadDto threadDto) {
    Thread thread = ThreadMapper.toEntity(threadDto);
    return ThreadMapper.toDto(threadRepository.save(thread));
  }

  @Override
  public Optional<ThreadDto> updateThread(Long id, ThreadDto threadDto) {
    return threadRepository.findById(id).map(existingThread -> {
      existingThread.setTitle(threadDto.title());
      return ThreadMapper.toDto(threadRepository.save(existingThread));
    });
  }

  @Override
  public boolean deleteThread(Long id) {
    if (threadRepository.existsById(id)) {
      threadRepository.deleteById(id);
      return true;
    }
    return false;
  }
}


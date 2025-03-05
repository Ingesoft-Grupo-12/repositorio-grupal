package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.ThreadDto;
import com.union.unionbackend.services.threadService.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
public class ThreadController {

  private final ThreadService threadService;

  @GetMapping
  public ResponseEntity<Page<ThreadDto>> getAllThreads(Pageable pageable) {
    return ResponseEntity.ok(threadService.getAllThreads(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ThreadDto> getThreadById(@PathVariable Long id) {
    Optional<ThreadDto> thread = threadService.getThreadById(id);
    return thread.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ThreadDto> createThread(@RequestBody ThreadDto threadDto) {
    return ResponseEntity.ok(threadService.createThread(threadDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ThreadDto> updateThread(@PathVariable Long id, @RequestBody ThreadDto threadDto) {
    Optional<ThreadDto> updatedThread = threadService.updateThread(id, threadDto);
    return updatedThread.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
    if (threadService.deleteThread(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}

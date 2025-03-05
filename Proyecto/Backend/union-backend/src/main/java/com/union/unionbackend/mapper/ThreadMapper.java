package com.union.unionbackend.mapper;

import com.union.unionbackend.dtos.ThreadDto;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.models.Thread;

import java.util.stream.Collectors;

public class ThreadMapper {

  public static ThreadDto toDto(Thread thread) {
    return new ThreadDto(
        thread.getId(),
        thread.getTitle(),
        thread.getCourse().getId(),
        thread.getCreatedAt(),
        thread.getMessages().stream().map(message -> message.getId()).collect(Collectors.toSet())
    );
  }

  public static Thread toEntity(ThreadDto threadDto) {
    Thread thread = Thread.builder()
        .id(threadDto.id())
        .title(threadDto.title())
        .createdAt(threadDto.createdAt())
        .course(Course.builder().id(threadDto.courseId()).build())  // Relación con Course
        .build();
    return thread;
  }
}

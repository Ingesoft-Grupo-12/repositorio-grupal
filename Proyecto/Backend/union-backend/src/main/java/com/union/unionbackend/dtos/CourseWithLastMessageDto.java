package com.union.unionbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CourseWithLastMessageDto {
  private Long courseId;
  private String courseCode;
  private String courseName;
  private String courseDescription;
  private String lastMessageContent;
  private LocalDateTime lastMessageTime;
  private String lastMessageSenderName;
}
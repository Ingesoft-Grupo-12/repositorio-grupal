package com.UNion.union_backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chatparticipants")
public class ChatParticipant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "chat_id")
  private Chat chat;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
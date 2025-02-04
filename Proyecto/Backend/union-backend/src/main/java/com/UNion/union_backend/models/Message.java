package com.UNion.union_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  private String content;

  @Column(name = "timestamp")
  private Instant timestamp;

  @ManyToOne
  @JoinColumn(name = "chat_id")
  private Chat chat;
}

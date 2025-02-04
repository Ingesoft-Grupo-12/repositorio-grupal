package com.union.unionbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;

/**
 * Model class for messages. Represents a message with its ID, chat ID, sender ID, content, and
 * timestamp.
 */
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

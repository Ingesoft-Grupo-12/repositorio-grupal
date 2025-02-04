package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
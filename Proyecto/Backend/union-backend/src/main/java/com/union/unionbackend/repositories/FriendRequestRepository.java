package com.union.unionbackend.repositories;

import com.union.unionbackend.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Message entities. Provides CRUD operations for Message entities.
 */
@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

}
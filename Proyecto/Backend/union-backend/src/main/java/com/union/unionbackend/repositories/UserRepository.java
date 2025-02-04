package com.union.unionbackend.repositories;

import com.union.unionbackend.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Message entities. Provides CRUD operations for Message entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByRole(String role);
}
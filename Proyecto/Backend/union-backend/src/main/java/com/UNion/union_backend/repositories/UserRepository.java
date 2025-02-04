package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAllByRole(String role);
}
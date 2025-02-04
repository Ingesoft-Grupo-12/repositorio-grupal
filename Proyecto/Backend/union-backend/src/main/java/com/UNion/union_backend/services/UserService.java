package com.UNion.union_backend.services;

import com.UNion.union_backend.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  List<User> getAllUsers();
  List<User> getUsersByRole(String role);
}

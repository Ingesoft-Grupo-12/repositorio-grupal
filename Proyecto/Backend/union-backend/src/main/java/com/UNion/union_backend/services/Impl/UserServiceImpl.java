package com.UNion.union_backend.services.Impl;

import com.UNion.union_backend.models.User;
import com.UNion.union_backend.repositories.UserRepository;
import com.UNion.union_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersByRole(String role) {
    return userRepository.findAllByRole(role);
  }
}

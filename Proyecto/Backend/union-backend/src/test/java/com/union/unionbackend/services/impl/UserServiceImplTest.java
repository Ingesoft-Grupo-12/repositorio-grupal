package com.union.unionbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.union.unionbackend.models.User;
import com.union.unionbackend.repositories.UserRepository;
import com.union.unionbackend.services.impl.UserServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllUsers() {
    //arrrange
    User user1 = new User(1L, "test_user", "teacher@gmail.com", "teacher", "image_example");
    List<User> users = List.of(user1);

    //act
    when(userRepository.findAll()).thenReturn((users));
    List<User> usersTest = userService.getAllUsers();

    //assert
    assertEquals(usersTest, users);
  }

  @Test
  void getUsersByRole(){
    //arrrange
    User user1 = new User(1L, "test_user", "teacher@gmail.com", "teacher", "image_example");
    List<User> users = List.of(user1);

    //act
    when(userRepository.findAllByRole("teacher")).thenReturn((users));
    List<User> usersTest = userService.getUsersByRole("teacher");

    //assert
    assertEquals(usersTest, users);
  }
}
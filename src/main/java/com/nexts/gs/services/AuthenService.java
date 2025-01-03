package com.nexts.gs.services;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nexts.gs.dto.request.SignupRequestDto;
import com.nexts.gs.model.User;
import com.nexts.gs.repository.UserRepository;

@Service
public class AuthenService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User signup(SignupRequestDto request) {
    // Check if user already exists
    Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
    if (existingUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
    }

    // Hash the password
    String hashedPassword = passwordEncoder.encode(request.getPassword());
    request.setPassword(hashedPassword);

    // Create a new user
    User newUser = new User();
    newUser.setUsername(request.getUsername());
    newUser.setPassword(request.getPassword());
    newUser.setRoles(Arrays.asList("USER"));
    // Set other fields as needed

    User savedUser = userRepository.save(newUser);

    // Return the user without the password
    savedUser.setPassword(null);
    return savedUser;
  }
}

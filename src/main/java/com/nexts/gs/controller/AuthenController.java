package com.nexts.gs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.dto.request.SignupRequestDto;
import com.nexts.gs.model.User;
import com.nexts.gs.services.AuthenService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenController {

  @Autowired
  AuthenService authenService;

  @PostMapping("/signup")
  public User signup(SignupRequestDto request) {
    return authenService.signup(request);
  }

}

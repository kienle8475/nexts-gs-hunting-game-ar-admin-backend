package com.nexts.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.model.GameSession;
import com.nexts.gs.services.GameSessionsService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/report/game")
public class GameReportController {
  @Autowired
  private GameSessionsService gameSessionsService;

  @GetMapping("/list")
  public List<GameSession> getGameReport() {
    return gameSessionsService.getGameSessions();
  }

}

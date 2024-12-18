package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexts.gs.model.GameSession;
import com.nexts.gs.repository.HeinekenGameSessionsRepository;
import com.nexts.gs.repository.TigerGameSessionsRepository;

@Service
public class GameSessionsService {

  private final HeinekenGameSessionsRepository heinekenGameSessionsRepository;
  private final TigerGameSessionsRepository tigerGameSessionsRepository;

  public GameSessionsService(
      HeinekenGameSessionsRepository heinekenGameSessionsRepository,
      TigerGameSessionsRepository tigetGameSessionsRepository) {
    this.heinekenGameSessionsRepository = heinekenGameSessionsRepository;
    this.tigerGameSessionsRepository = tigetGameSessionsRepository;
  }

  public List<GameSession> getGameSessions() {
    List<GameSession> combinedGameSessions = new ArrayList<>();
    heinekenGameSessionsRepository.findAll().forEach((GameSession gameSession) -> {
      combinedGameSessions.add(gameSession);
    });

    tigerGameSessionsRepository.findAll().forEach((GameSession gameSession) -> {
      combinedGameSessions.add(gameSession);
    });

    return combinedGameSessions;
  }

}

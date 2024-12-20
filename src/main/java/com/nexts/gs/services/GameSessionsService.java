package com.nexts.gs.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.GameSessionDto;
import com.nexts.gs.mapper.GameSessionMapper;
import com.nexts.gs.model.GameSession;
import com.nexts.gs.repository.heineken.HeinekenGameSessionsRepository;
import com.nexts.gs.repository.tiger.TigerGameSessionsRepository;

@Service
public class GameSessionsService {

  private final HeinekenGameSessionsRepository heinekenGameSessionsRepository;
  private final TigerGameSessionsRepository tigerGameSessionsRepository;
  private final GameSessionMapper gameSessionMapper;

  public GameSessionsService(
      HeinekenGameSessionsRepository heinekenGameSessionsRepository,
      TigerGameSessionsRepository tigetGameSessionsRepository,
      GameSessionMapper gameSessionMapper) {
    this.heinekenGameSessionsRepository = heinekenGameSessionsRepository;
    this.tigerGameSessionsRepository = tigetGameSessionsRepository;
    this.gameSessionMapper = gameSessionMapper;
  }

  @Cacheable(value = "gameSessions")
  public List<GameSessionDto> getGameSessions() {
    List<GameSessionDto> combinedGameSessions = new ArrayList<>();
    heinekenGameSessionsRepository.findAll().forEach((GameSession gameSession) -> {
      combinedGameSessions.add(gameSessionMapper.toDto(gameSession));
    });

    tigerGameSessionsRepository.findAll().forEach((GameSession gameSession) -> {
      combinedGameSessions.add(gameSessionMapper.toDto(gameSession));
    });

    return combinedGameSessions;
  }

  @Cacheable(value = "heinekenGameSessions")
  public Page<GameSession> getHeinekenGameSessionsWithFilters(Instant startDate, Instant endDate, String outletId,
      Pageable pageable) {
    return heinekenGameSessionsRepository.findGameSessionsWithFilters(startDate,
        endDate, outletId, pageable);
  }

  @Cacheable(value = "tigerGameSessions")
  public Page<GameSession> getTigerGameSessionsWithFilters(Instant startDate,
      Instant endDate, String outletId,
      Pageable pageable) {
    return tigerGameSessionsRepository.findGameSessionsWithFilters(startDate,
        endDate, outletId, pageable);
  }

}

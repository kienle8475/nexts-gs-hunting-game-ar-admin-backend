package com.nexts.gs.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.dto.GameSessionDto;
import com.nexts.gs.model.GameSession;
import com.nexts.gs.services.GameSessionsService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/report/game")
public class GameReportController {
  @Autowired
  private GameSessionsService gameSessionsService;

  @GetMapping("/list")
  public List<GameSessionDto> getGameReport() {
    return gameSessionsService.getGameSessions();
  }

  @GetMapping("/heineken")
  public Page<GameSession> getHeinekenGameSessions(
      @RequestParam(required = false) Instant startDate,
      @RequestParam(required = false) Instant endDate,
      @RequestParam(required = false) String outletId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    PageRequest pageable = PageRequest.of(page, size);
    return gameSessionsService.getHeinekenGameSessionsWithFilters(startDate,
        endDate, outletId, pageable);
  }

  @GetMapping("/tiger")
  public Page<GameSession> getTigerGameSessions(
      @RequestParam(required = false) Instant startDate,
      @RequestParam(required = false) Instant endDate,
      @RequestParam(required = false) String outletId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    PageRequest pageable = PageRequest.of(page, size);
    return gameSessionsService.getTigerGameSessionsWithFilters(startDate,
        endDate, outletId, pageable);
  }
}

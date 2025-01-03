package com.nexts.gs.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.GameSession;
import com.nexts.gs.services.GameSessionsService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/report/game")
public class GameReportController {
  @Autowired
  private GameSessionsService gameSessionsService;

  @GetMapping("/list")
  public Page<GameSession> getHeinekenGameSessions(
      @RequestParam(required = true) String userId,
      @RequestParam(required = false) Instant startDate,
      @RequestParam(required = false) Instant endDate,
      @RequestParam(required = false) String outletId,
      @RequestParam(defaultValue = "BOTH") BoothTypeEnum boothType,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "25") int size) {
    // Set default startDate to the beginning of today if null
    if (startDate == null) {
      startDate = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    // Set default endDate to the end of today if null
    if (endDate == null) {
      endDate = LocalDate.now().atTime(23, 59, 59, 999999999).toInstant(ZoneOffset.UTC);
    }
    PageRequest pageable = PageRequest.of(page, size);
    return gameSessionsService.getGameSessionsWithFilters(
        userId, boothType, startDate,
        endDate, outletId, pageable);
  }
}

package com.nexts.gs.services;

import java.time.Instant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.GameSession;
import com.nexts.gs.repository.heineken.HeinekenGameSessionsRepository;
import com.nexts.gs.repository.tiger.TigerGameSessionsRepository;

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

  public Page<GameSession> getGameSessionsWithFilters(BoothTypeEnum boothType, Instant startDate,
      Instant endDate, String outletId,
      Pageable pageable) {
    Pageable sortedPageable = PageRequest.of(
        pageable.getPageNumber(),
        pageable.getPageSize(),
        Sort.by(Sort.Direction.DESC, "created_utc"));
    switch (boothType) {
      case HEINEKEN:
        if (outletId != null && startDate != null) {
          return heinekenGameSessionsRepository.findGameSessionsByOutetAndDateRange(startDate, endDate, outletId,
              sortedPageable);
        } else if (outletId != null && startDate == null) {
          return heinekenGameSessionsRepository.findGameSessionsByOutlet(outletId,
              sortedPageable);
        } else if (outletId == null && startDate != null) {
          return heinekenGameSessionsRepository.findGameSessionsByDateRange(startDate, endDate,
              sortedPageable);
        } else {
          return heinekenGameSessionsRepository.findAll(sortedPageable);
        }

      case TIGER:
        if (outletId != null && startDate != null) {
          return tigerGameSessionsRepository.findGameSessionsByOutetAndDateRange(startDate, endDate, outletId,
              sortedPageable);
        } else if (outletId != null && startDate == null) {
          return tigerGameSessionsRepository.findGameSessionsByOutlet(outletId,
              sortedPageable);
        } else if (outletId == null && startDate != null) {
          return tigerGameSessionsRepository.findGameSessionsByDateRange(startDate, endDate,
              sortedPageable);
        } else {
          return tigerGameSessionsRepository.findAll(sortedPageable);
        }
      default:
        return heinekenGameSessionsRepository.findAll(sortedPageable);
    }
  }
}

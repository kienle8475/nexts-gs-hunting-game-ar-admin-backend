package com.nexts.gs.repository.tiger;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexts.gs.model.GameSession;

@Repository
public interface TigerGameSessionsRepository
    extends MongoRepository<GameSession, String> {

  @Query("{ '$and': [ " +
      "  { '$or': [ { 'created_utc': { '$gte': ?0, '$lte': ?1 } }, { } ] }, " +
      "  { '$or': [ { 'outlet.id': ?2 }, { } ] } " +
      "] }")
  Page<GameSession> findGameSessionsWithFilters(Instant startDate, Instant endDate, String outletId, Pageable pageable);

}

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

  @Query("{ 'outlet.id': ?0}")
  Page<GameSession> findGameSessionsByOutlet(String outletId, Pageable pageable);

  @Query("{ 'created_utc': { '$gte': ?0, '$lte': ?1 } }")
  Page<GameSession> findGameSessionsByDateRange(Instant startDate, Instant enđate, Pageable pageable);

  @Query("{'$and': [ { 'created_utc': { '$gte': ?0, '$lte': ?1 } }, { 'outlet.id': ?2 } ]}")
  Page<GameSession> findGameSessionsByOutetAndDateRange(Instant startDate, Instant enđate, String outletId,
      Pageable pageable);
}

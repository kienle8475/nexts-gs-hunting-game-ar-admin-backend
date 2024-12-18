package com.nexts.gs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nexts.gs.model.GameSession;

@Repository
public interface TigerGameSessionsRepository extends MongoRepository<GameSession, String> {

}

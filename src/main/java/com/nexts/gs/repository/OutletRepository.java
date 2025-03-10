package com.nexts.gs.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;

@Repository
public interface OutletRepository extends MongoRepository<Outlet, String> {

  @Query(value = "{ 'boothType': ?0 }", fields = "{ '_id': 1 }")
  List<Outlet> findIdsByBoothType(BoothTypeEnum boothType);

  List<Outlet> findByIdIn(List<ObjectId> ids);
}

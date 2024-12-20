package com.nexts.gs.repository.tiger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nexts.gs.model.Outlet;

@Repository
public interface TigerOutletRepository extends MongoRepository<Outlet, String> {

}
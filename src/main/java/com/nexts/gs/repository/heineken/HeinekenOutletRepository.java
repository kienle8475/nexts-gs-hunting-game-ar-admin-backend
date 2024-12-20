package com.nexts.gs.repository.heineken;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nexts.gs.model.Outlet;

@Repository
public interface HeinekenOutletRepository extends MongoRepository<Outlet, String> {

}

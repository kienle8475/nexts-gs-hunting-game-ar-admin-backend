package com.nexts.gs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nexts.gs.model.Province;

@Repository
public interface ProvinceRepository extends MongoRepository<Province, String> {

}

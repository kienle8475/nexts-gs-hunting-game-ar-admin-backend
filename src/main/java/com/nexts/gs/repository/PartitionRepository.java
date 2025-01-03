package com.nexts.gs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nexts.gs.model.Partition;

public interface PartitionRepository extends MongoRepository<Partition, String> {

}

package com.nexts.gs.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.GameSession;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.model.Partition;
import com.nexts.gs.model.User;
import com.nexts.gs.repository.OutletRepository;
import com.nexts.gs.repository.PartitionRepository;
import com.nexts.gs.repository.UserRepository;

@Service
public class GameSessionsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PartitionRepository partitionRepository;

  @Autowired
  OutletRepository outletRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<ObjectId> getOutletIdsAsObjectIdByBoothType(BoothTypeEnum boothType) {
    // Query repository for outlets matching the boothType
    List<Outlet> outlets = outletRepository.findIdsByBoothType(boothType);
    // Extract ObjectId
    return outlets.stream()
        .map(outlet -> new ObjectId(outlet.getId()))
        .collect(Collectors.toList());
  }

  public List<ObjectId> getOutletIdsAsObjectIdByPartition(String partitionId) {
    Optional<Partition> partition = partitionRepository.findById(partitionId);
    return partition.get().getOutlets().stream()
        .map(ObjectId::new)
        .collect(Collectors.toList());
  }

  public Page<GameSession> getGameSessionsWithFilters(String userId, BoothTypeEnum boothType, Instant startDate,
      Instant endDate, String outletId, Pageable pageable) {

    try {
      Pageable sortedPageable = PageRequest.of(
          pageable.getPageNumber(),
          pageable.getPageSize(),
          Sort.by(Sort.Direction.DESC, "created_utc"));
      // Query user
      Optional<User> user = userRepository.findById(userId);
      // Query group

      // Initial query
      Query query = new Query();
      List<Criteria> criteriaList = new ArrayList<>();

      // Criteria by daterangre
      if (startDate != null && endDate != null) {
        criteriaList.add(Criteria.where("createdUtc").gte(startDate).lte(endDate));
      }

      // Criteria by outlet
      if (outletId != null) {
        criteriaList.add(Criteria.where("outlet.id").is(outletId));
      } else if (user.get().getGroup() != null) {
        List<ObjectId> partitionIds = getOutletIdsAsObjectIdByPartition(user.get().getGroup());
        if (boothType != BoothTypeEnum.BOTH) {
          List<ObjectId> outletIds = getOutletIdsAsObjectIdByBoothType(boothType);
          partitionIds.retainAll(outletIds);
        }
        criteriaList.add(Criteria.where("outlet").in(partitionIds));

      }
      // Combine criteria if any are present
      if (!criteriaList.isEmpty()) {
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
      }

      // Apply pagination
      query.with(sortedPageable);

      // Fetch the data
      List<GameSession> gameSessions = mongoTemplate.find(query, GameSession.class);

      // Count total records for pagination
      long count = mongoTemplate.count(query.skip(-1).limit(-1), GameSession.class);

      return PageableExecutionUtils.getPage(gameSessions, sortedPageable, () -> count);
    } catch (Exception e) {
      throw new RuntimeException("Unable to fetch game sessions", e);
    }

  }

}

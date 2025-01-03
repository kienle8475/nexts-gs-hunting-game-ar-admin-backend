package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newOutletRequestDto;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.model.Partition;
import com.nexts.gs.model.Province;
import com.nexts.gs.model.User;
import com.nexts.gs.repository.OutletRepository;
import com.nexts.gs.repository.PartitionRepository;
import com.nexts.gs.repository.UserRepository;

@Service
public class OutletService {
  private final OutletRepository outletRepository;
  private final ProvinceService provinceService;
  private final UserRepository userRepository;
  private final PartitionRepository partitionRepository;

  public OutletService(
      ProvinceService provinceService,
      OutletRepository outletRepository,
      UserRepository userRepository,
      PartitionRepository partitionRepository) {
    this.outletRepository = outletRepository;
    this.provinceService = provinceService;
    this.userRepository = userRepository;
    this.partitionRepository = partitionRepository;
  }

  public List<ObjectId> getOutletIdsAsObjectIdByPartition(String partitionId) {
    Optional<Partition> partition = partitionRepository.findById(partitionId);
    return partition.get().getOutlets().stream()
        .map(ObjectId::new)
        .collect(Collectors.toList());
  }

  public List<Outlet> getAllOutlets(String userId, BoothTypeEnum boothType) {
    List<Outlet> combinedOutlets = new ArrayList<>();
    Optional<User> user = userRepository.findById(userId);
    if (user.get().getGroup() != null) {
      List<ObjectId> idsByPartition = getOutletIdsAsObjectIdByPartition(user.get().getGroup());
      combinedOutlets = outletRepository.findByIdIn(idsByPartition);
    } else {
      combinedOutlets = outletRepository.findAll();
    }
    if (boothType != BoothTypeEnum.BOTH) {
      return combinedOutlets.stream().filter(outlet -> outlet.getBoothType() == boothType).collect(Collectors.toList());
    }
    return combinedOutlets;
  }

  public Outlet addNewOutlet(newOutletRequestDto newOutletRequest) {
    Outlet outlet = new Outlet();
    Province province = provinceService.findProvinceById(newOutletRequest.getProvinceId());
    outlet.setName(newOutletRequest.getName());
    outlet.setAddress(newOutletRequest.getAddress());
    outlet.setProvince(province.getId());
    switch (newOutletRequest.getBoothType()) {
      case HEINEKEN:
        return outletRepository.save(outlet);
      case TIGER:
        return outletRepository.save(outlet);
      default:
        return outletRepository.save(outlet);
    }
  }

}

package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newOutletRequestDto;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.model.Province;
import com.nexts.gs.model.User;
import com.nexts.gs.repository.OutletRepository;
import com.nexts.gs.repository.UserRepository;

@Service
public class OutletService {
  private final OutletRepository outletRepository;
  private final ProvinceService provinceService;
  private final UserRepository userRepository;

  public OutletService(
      ProvinceService provinceService,
      OutletRepository outletRepository,
      UserRepository userRepository) {
    this.outletRepository = outletRepository;
    this.provinceService = provinceService;
    this.userRepository = userRepository;
  }

  @Cacheable(value = "outlets")
  public List<Outlet> getAllOutlets(String userId, BoothTypeEnum boothType) {
    List<Outlet> combinedOutlets = new ArrayList<>();
    Optional<User> user = userRepository.findById(userId);
    if (user.get().getGroup() != null) {
      combinedOutlets.addAll(outletRepository.findAll());
    } else {
      switch (boothType) {
        case HEINEKEN:
          combinedOutlets.addAll(outletRepository.findAll());
          break;
        case TIGER:
          combinedOutlets.addAll(outletRepository.findAll());
          break;
        default:
          combinedOutlets.addAll(outletRepository.findAll());
          break;
      }
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

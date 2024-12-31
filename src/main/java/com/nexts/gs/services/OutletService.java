package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newOutletRequestDto;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.mapper.OutletMapper;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.model.Province;
import com.nexts.gs.repository.heineken.HeinekenOutletRepository;
import com.nexts.gs.repository.tiger.TigerOutletRepository;

@Service
public class OutletService {
  private final HeinekenOutletRepository heinekenOutletRepository;
  private final TigerOutletRepository tigerOutletRepository;
  private final ProvinceService provinceService;

  public OutletService(@Qualifier("heinekenOutletRepository") HeinekenOutletRepository heinekenOutletRepository,
      @Qualifier("tigerOutletRepository") TigerOutletRepository tigerOutletRepository,
      @Qualifier("provinceService") ProvinceService provinceService) {
    this.heinekenOutletRepository = heinekenOutletRepository;
    this.tigerOutletRepository = tigerOutletRepository;
    this.provinceService = provinceService;
  }

  @Cacheable(value = "outlets")
  public List<Outlet> getAllOutlets(BoothTypeEnum boothType) {
    List<Outlet> combinedOutlets = new ArrayList<>();
    switch (boothType) {
      case HEINEKEN:
        combinedOutlets.addAll(heinekenOutletRepository.findAll());
        break;
      case TIGER:
        combinedOutlets.addAll(tigerOutletRepository.findAll());
        break;
      default:
        combinedOutlets.addAll(tigerOutletRepository.findAll());
        break;
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
        return heinekenOutletRepository.save(outlet);
      case TIGER:
        return tigerOutletRepository.save(outlet);
      default:
        Outlet db1Outlet = heinekenOutletRepository.save(outlet);
        return tigerOutletRepository.save(db1Outlet);
    }
  }

}

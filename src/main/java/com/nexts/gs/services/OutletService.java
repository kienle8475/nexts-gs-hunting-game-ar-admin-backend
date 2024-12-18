package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newOutletRequest;
import com.nexts.gs.dto.response.OutletResponse;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.model.Province;
import com.nexts.gs.repository.HeinekenOutletRepository;
import com.nexts.gs.repository.TigerOutletRepository;

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

  public List<OutletResponse> getAllOutlets() {
    List<OutletResponse> combinedOutlets = new ArrayList<>();
    heinekenOutletRepository.findAll().forEach((Outlet outlet) -> {
      OutletResponse outletResponse = new OutletResponse(outlet);
      outletResponse.setOutletType(BoothTypeEnum.HEINEKEN);
      combinedOutlets.add(outletResponse);
    });

    tigerOutletRepository.findAll().forEach((Outlet outlet) -> {
      OutletResponse outletResponse = new OutletResponse(outlet);
      outletResponse.setOutletType(BoothTypeEnum.TIGER);
      combinedOutlets.add(outletResponse);
    });

    return combinedOutlets;
  }

  public Outlet addNewOutlet(newOutletRequest newOutletRequest) {
    Outlet outlet = new Outlet();
    Province province = provinceService.findProvinceById(newOutletRequest.getProvinceId());
    outlet.setName(newOutletRequest.getName());
    outlet.setAddress(newOutletRequest.getAddress());
    outlet.setProvince(province);
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

package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newProvinceRequestDto;
import com.nexts.gs.model.Province;
import com.nexts.gs.repository.heineken.HeinekenProvinceRepository;
import com.nexts.gs.repository.tiger.TigerProvinceRepository;

@Service
public class ProvinceService {
  private final HeinekenProvinceRepository heinekenProvinceRepository;
  private final TigerProvinceRepository tigerProvinceRepository;

  public ProvinceService(
      @Qualifier("heinekenProvinceRepository") HeinekenProvinceRepository heinekenProvinceRepository,
      @Qualifier("tigerProvinceRepository") TigerProvinceRepository tigerProvinceRepository) {
    this.heinekenProvinceRepository = heinekenProvinceRepository;
    this.tigerProvinceRepository = tigerProvinceRepository;
  }

  public List<Province> getAllProvinces() {
    List<Province> combinedProvinces = new ArrayList<>();
    combinedProvinces.addAll(heinekenProvinceRepository.findAll());
    return combinedProvinces;
  }

  public Province findProvinceById(String id) {
    Optional<Province> province = heinekenProvinceRepository.findById(id);
    return province.orElseThrow(() -> new RuntimeException("Province not found with id: " + id));
  }

  public Province addNewProvince(newProvinceRequestDto newProvince) {
    Province province = new Province();
    province.setName(newProvince.getName());
    Province db1Province = heinekenProvinceRepository.save(province);
    return tigerProvinceRepository.save(db1Province);
  }
}

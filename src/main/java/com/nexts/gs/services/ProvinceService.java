package com.nexts.gs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nexts.gs.dto.request.newProvinceRequestDto;
import com.nexts.gs.model.Province;
import com.nexts.gs.repository.ProvinceRepository;

@Service
public class ProvinceService {
  private final ProvinceRepository provinceRepository;

  public ProvinceService(
      ProvinceRepository provinceRepository) {
    this.provinceRepository = provinceRepository;
  }

  @Cacheable(value = "provinces")
  public List<Province> getAllProvinces() {
    return provinceRepository.findAll();
  }

  public Province findProvinceById(String id) {
    Optional<Province> province = provinceRepository.findById(id);
    return province.orElseThrow(() -> new RuntimeException("Province not found with id: " + id));
  }

  public Province addNewProvince(newProvinceRequestDto newProvince) {
    Province province = new Province();
    province.setName(newProvince.getName());
    return provinceRepository.save(province);
  }
}

package com.nexts.gs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
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

  @Cacheable(value = "provinces")
  public List<Province> getAllProvinces() {
    List<Province> combinedProvinces = new ArrayList<>();
    combinedProvinces.addAll(heinekenProvinceRepository.findAll());
    return combinedProvinces;
  }

  @Cacheable(value = "provinces-detail")
  public List<Province> getAllProvincesDetail() {
    // Combine and merge provinces from Heineken and Tiger repositories
    List<Province> mergedProvinces = Stream.concat(
        heinekenProvinceRepository.findAll().stream(),
        tigerProvinceRepository.findAll().stream())
        .collect(Collectors.toMap(
            Province::getId,
            province -> {
              province.setOutlets(new ArrayList<>(province.getOutlets())); // Ensure a new list for outlets
              return province;
            },
            (existing, incoming) -> {
              existing.getOutlets().addAll(incoming.getOutlets());
              return existing;
            }))
        .values().stream().toList(); // Convert to List instead of an array
    return mergedProvinces;

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

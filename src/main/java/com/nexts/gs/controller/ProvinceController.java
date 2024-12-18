package com.nexts.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.dto.request.newProvinceRequest;
import com.nexts.gs.model.Province;
import com.nexts.gs.services.ProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/config/province")
public class ProvinceController {

  @Autowired
  private ProvinceService provinceService;

  @GetMapping("/list")
  public ResponseEntity<List<Province>> getAllProvince() {
    List<Province> provinces = provinceService.getAllProvinces();
    return ResponseEntity.ok(provinces);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addNewProvince(@RequestBody newProvinceRequest newProvince) {
    provinceService.addNewProvince(newProvince);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successful!");
  }

}

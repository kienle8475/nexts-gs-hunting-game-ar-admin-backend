package com.nexts.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.dto.request.newOutletRequestDto;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;
import com.nexts.gs.services.OutletService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/api/config/outlet")
public class OutletController {

  @Autowired
  private OutletService outletService;

  @GetMapping("/list")
  public ResponseEntity<List<Outlet>> getAllOutlet(
      @RequestParam(defaultValue = "BOTH") BoothTypeEnum bootType) {
    List<Outlet> outlets = outletService.getAllOutlets(bootType);
    return ResponseEntity.ok(outlets);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addNewOutlet(@RequestBody newOutletRequestDto newOutlet) {
    outletService.addNewOutlet(newOutlet);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successful!");
  }

}

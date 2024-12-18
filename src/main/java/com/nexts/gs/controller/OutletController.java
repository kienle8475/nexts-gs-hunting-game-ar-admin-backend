package com.nexts.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexts.gs.dto.request.newOutletRequest;
import com.nexts.gs.dto.response.OutletResponse;
import com.nexts.gs.services.OutletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/config/outlet")
public class OutletController {

  @Autowired
  private OutletService outletService;

  @GetMapping("/list")
  public ResponseEntity<List<OutletResponse>> getAllOutlete() {
    List<OutletResponse> outlets = outletService.getAllOutlets();
    return ResponseEntity.ok(outlets);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addNewOutlet(@RequestBody newOutletRequest newOutlet) {
    outletService.addNewOutlet(newOutlet);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successful!");
  }

}

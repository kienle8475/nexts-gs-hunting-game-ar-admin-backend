package com.nexts.gs.dto.response;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.nexts.gs.model.Outlet;

import lombok.Data;

@Data
public class ProvinceResponseDto {
  private String id;

  private String name;

  private Instant createdUtc;

  private Instant updatedUtc;

  private List<Outlet> outlets = new ArrayList<>();
}

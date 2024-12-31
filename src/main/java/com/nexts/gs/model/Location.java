package com.nexts.gs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {
  @Builder.Default
  private double lat = 0;

  @Builder.Default
  private double lng = 0;

  @Builder.Default
  private int radius = 250;
}

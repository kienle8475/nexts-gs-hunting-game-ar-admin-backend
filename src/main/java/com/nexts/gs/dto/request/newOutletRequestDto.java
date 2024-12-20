package com.nexts.gs.dto.request;

import com.nexts.gs.enums.BoothTypeEnum;

import lombok.Data;

@Data
public class newOutletRequestDto {
  private String name;
  private String address;
  private String provinceId;
  private BoothTypeEnum boothType;
}
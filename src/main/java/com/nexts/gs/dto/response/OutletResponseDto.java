package com.nexts.gs.dto.response;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Province;

import lombok.Data;

@Data
public class OutletResponseDto {
  private String id;

  private String name;

  private String address;

  private Province province;

  private BoothTypeEnum outletType;

}

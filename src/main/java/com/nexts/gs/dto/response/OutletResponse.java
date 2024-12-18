package com.nexts.gs.dto.response;

import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class OutletResponse extends Outlet {
  private BoothTypeEnum outletType;

  public OutletResponse(Outlet outlet) {
    super(); // Calls parent Outlet's no-args constructor
    this.setId(outlet.getId());
    this.setName(outlet.getName());
    this.setAddress(outlet.getAddress());
    this.setProvince(outlet.getProvince());
    this.setCreatedUtc(outlet.getCreatedUtc());
    this.setUpdatedUtc(outlet.getUpdatedUtc());
  }
}

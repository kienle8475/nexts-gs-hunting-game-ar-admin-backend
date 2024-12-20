package com.nexts.gs.mapper;

import org.mapstruct.Mapper;

import com.nexts.gs.dto.OutletDto;
import com.nexts.gs.dto.request.newOutletRequestDto;
import com.nexts.gs.dto.response.OutletResponseDto;
import com.nexts.gs.enums.BoothTypeEnum;
import com.nexts.gs.model.Outlet;

@Mapper(componentModel = "spring")
public interface OutletMapper {
  OutletDto toDto(Outlet outlet);

  OutletResponseDto toResponseDto(Outlet outlet, BoothTypeEnum outletType);

  newOutletRequestDto toRequestDto(Outlet outlet, String provinceId);

}

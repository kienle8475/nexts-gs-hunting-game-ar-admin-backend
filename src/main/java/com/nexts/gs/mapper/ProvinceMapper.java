package com.nexts.gs.mapper;

import org.mapstruct.Mapper;

import com.nexts.gs.dto.ProvinceDto;
import com.nexts.gs.dto.request.newProvinceRequestDto;
import com.nexts.gs.model.Province;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
  ProvinceDto toDto(Province province);

  newProvinceRequestDto toRequestDto(Province province);
}

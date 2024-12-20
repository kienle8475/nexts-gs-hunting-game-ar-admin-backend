package com.nexts.gs.mapper;

import org.mapstruct.Mapper;

import com.nexts.gs.dto.GiftDto;
import com.nexts.gs.model.Gift;

@Mapper(componentModel = "spring")
public interface GiftMapper {
  GiftDto toDto(Gift gift);
}

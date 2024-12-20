package com.nexts.gs.mapper;

import org.mapstruct.Mapper;

import com.nexts.gs.dto.GameSessionDto;
import com.nexts.gs.model.GameSession;

@Mapper(componentModel = "spring")
public interface GameSessionMapper {
  GameSessionDto toDto(GameSession gameSession);
}

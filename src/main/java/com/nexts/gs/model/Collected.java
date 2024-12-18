package com.nexts.gs.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.nexts.gs.enums.ElementTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collected {
  @Field("name")
  private ElementTypeEnum name;

  private Integer count;
}

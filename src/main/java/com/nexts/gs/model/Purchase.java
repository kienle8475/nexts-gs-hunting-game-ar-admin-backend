package com.nexts.gs.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.nexts.gs.enums.PackTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

  @Field("package_type")
  private PackTypeEnum packageType;

  @Field("beer_type")
  private String beerType;

  private Integer quantity;
}

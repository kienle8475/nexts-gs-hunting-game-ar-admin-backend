package com.nexts.gs.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "hunters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hunter {

  @Id
  private String id;

  private String name;

  @Indexed(unique = true)
  private String phone;

  @Field("created_utc")
  @CreatedDate
  private Instant createdUtc;

  @Field("updated_utc")
  @LastModifiedDate
  private Instant updatedUtc;

}

package com.nexts.gs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "outlets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outlet {
  @Id
  private String id;

  @Indexed(unique = true)
  private String name;

  private String address;

  @DocumentReference
  private Province province;

  @Field("created_utc")
  @CreatedDate
  private Instant createdUtc;

  @Field("updated_utc")
  @LastModifiedDate
  private Instant updatedUtc;
}

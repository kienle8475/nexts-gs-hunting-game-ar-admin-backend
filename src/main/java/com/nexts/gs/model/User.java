package com.nexts.gs.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  private String password;

  private List<String> roles;

  private String group;

  @Field("created_utc")
  @CreatedDate
  private Instant createdUtc;

  @Field("updated_utc")
  @LastModifiedDate
  private Instant updatedUtc;

}

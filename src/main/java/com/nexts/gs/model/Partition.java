package com.nexts.gs.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "partitions")
public class Partition {
  @Id
  private String id;

  private String name;

  private List<String> outlets = new ArrayList<>();

}

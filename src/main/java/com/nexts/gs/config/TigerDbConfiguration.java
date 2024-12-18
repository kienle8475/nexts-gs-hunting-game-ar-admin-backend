package com.nexts.gs.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.nexts.gs.repository", mongoTemplateRef = TigerDbConfiguration.MONGO_TEMPLATE)
public class TigerDbConfiguration {
  protected static final String MONGO_TEMPLATE = "tigerMongoTemplate";

  private final MongoDatabaseFactory tigerMongoDatabaseFactory;

  public TigerDbConfiguration(@Qualifier("tigerMongoFactory") MongoDatabaseFactory tigerMongoDatabaseFactory) {
    this.tigerMongoDatabaseFactory = tigerMongoDatabaseFactory;
  }

  @Bean(name = "tigerMongoTemplate")
  public MongoTemplate tigerMongoTemplate() {
    return new MongoTemplate(tigerMongoDatabaseFactory);
  }

}

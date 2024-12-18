package com.nexts.gs.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.nexts.gs.repository", mongoTemplateRef = HeinekenDbConfiguration.MONGO_TEMPLATE)
public class HeinekenDbConfiguration {
  protected static final String MONGO_TEMPLATE = "heinekenMongoTemplate";

  private final MongoDatabaseFactory heinekenMongoDatabaseFactory;

  public HeinekenDbConfiguration(@Qualifier("heinekenMongoFactory") MongoDatabaseFactory heinekenMongoDatabaseFactory) {
    this.heinekenMongoDatabaseFactory = heinekenMongoDatabaseFactory;
  }

  @Primary
  @Bean(name = "heinekenMongoTemplate")
  public MongoTemplate heinekenMongoTemplate() {
    return new MongoTemplate(heinekenMongoDatabaseFactory);
  }

}

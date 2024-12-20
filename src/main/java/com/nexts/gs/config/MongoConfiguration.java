package com.nexts.gs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration {

  // Inject MongoDB URI for Heineken
  @Value("${spring.data.mongodb.heineken.uri}")
  private String heinekenDatabaseUri;

  @Primary
  @Bean(name = "heinekenMongoClient")
  public MongoClient heinekenMongoClient() {
    return MongoClients.create(heinekenDatabaseUri);
  }

  // MongoDatabaseFactory and MongoTemplate for Heineken
  @Primary
  @Bean(name = "heinekenMongoFactory")
  public MongoDatabaseFactory heinekenMongoFactory() {
    return new SimpleMongoClientDatabaseFactory(heinekenMongoClient(), getDatabaseName(heinekenDatabaseUri));
  }

  // Inject MongoDB URI for Tiger
  @Value("${spring.data.mongodb.tiger.uri}")
  private String tigerDatabaseUri;

  @Bean(name = "tigerMongoClient")
  public MongoClient tigerMongoClient() {
    return MongoClients.create(tigerDatabaseUri);
  }

  // MongoDatabaseFactory and MongoTemplate for Tiger
  @Bean(name = "tigerMongoFactory")
  public MongoDatabaseFactory tigerMongoFactory() {
    return new SimpleMongoClientDatabaseFactory(tigerMongoClient(), getDatabaseName(tigerDatabaseUri));

  }

  private String getDatabaseName(String uri) {
    return uri.substring(uri.lastIndexOf("/") + 1);
  }
}

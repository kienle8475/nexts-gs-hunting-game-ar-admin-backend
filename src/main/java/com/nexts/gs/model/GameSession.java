package com.nexts.gs.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nexts.gs.enums.GameModeEnum;
import com.nexts.gs.enums.GameResultEnum;
import com.nexts.gs.enums.GameStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "gamesessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSession {

  @Id
  private String id;

  private String nickname;

  private GameModeEnum mode;

  private GameStatusEnum status;

  private GameResultEnum result;

  @Field("bill_image_url")
  private String billImageUrl;

  @Field("created_utc")
  @CreatedDate
  private Instant createdUtc;

  @Field("updated_utc")
  @LastModifiedDate
  private Instant updatedUtc;

  @DocumentReference
  private Hunter hunter;

  @DocumentReference
  private Outlet outlet;

  private List<Purchase> purchases = new ArrayList<>();

  private List<Collected> collected = new ArrayList<>();

  @DocumentReference
  @Field("reward_gift")
  private Gift rewardGift;

}

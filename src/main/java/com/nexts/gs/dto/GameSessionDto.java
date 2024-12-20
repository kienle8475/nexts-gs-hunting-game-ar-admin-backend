package com.nexts.gs.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.nexts.gs.enums.GameModeEnum;
import com.nexts.gs.enums.GameResultEnum;
import com.nexts.gs.enums.GameStatusEnum;
import com.nexts.gs.model.Collected;
import com.nexts.gs.model.Purchase;

import lombok.Data;

@Data
public class GameSessionDto {

  private String id;

  private GameModeEnum mode;

  private GameStatusEnum status;

  private GameResultEnum result;

  private String billImageUrl;

  private Instant createdUtc;

  private HunterDto hunter;

  private OutletDto outlet;

  private GiftDto rewardGift;

  private List<Purchase> purchases = new ArrayList<>();

  private List<Collected> collected = new ArrayList<>();
}

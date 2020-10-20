package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class GameData {

    List<Card> cardStack;

    List<User> players;

    Map<User, PlayerData> playerDataMap;
}

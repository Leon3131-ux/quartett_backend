package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GameData {

    List<Card> cardStack;

    List<User> players;

    Map<User, PlayerData> playerDataMap;
}

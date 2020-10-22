package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PlayerData {

    List<Card> cards;

    List<CardSet> completedCardSets;

}

package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.repository.CardDeckRepository;
import org.springframework.stereotype.Service;

@Service
public class CardDeckService extends BaseService<CardDeck, Long> {

    public CardDeckService(CardDeckRepository cardDeckRepository){
        super(cardDeckRepository);
    }

}

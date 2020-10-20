package com.nickleback.quartettBackend.init;

import com.nickleback.quartettBackend.domain.Card;
import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.CardSet;
import com.nickleback.quartettBackend.repository.CardDeckRepository;
import com.nickleback.quartettBackend.repository.CardRepository;
import com.nickleback.quartettBackend.repository.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final CardRepository cardRepository;
    private final CardSetRepository cardSetRepository;
    private final CardDeckRepository cardDeckRepository;

    @PostConstruct
    private void initialize(){
        initializeCards();
        initializeCardSets();
        initializeDeck();
    }

    private void initializeCards(){
        for(int i = 0; i < 32; i++){
            cardRepository.save(new Card("card" + i, 0L, "", "", "", "", "", null));
        }
    }

    private void initializeCardSets(){
        int cardIndex = 0;
        List<Card> createdCards = cardRepository.findAll();
        for (int i = 0; i < 8; i++){
            List<Card> cards = new ArrayList<>();
            for (int f = 0; f < 4; f++){
                cards.add(createdCards.get(cardIndex));
                cardIndex++;
            }
            cardSetRepository.save(new CardSet("A", cards));
        }
    }

    private void initializeDeck(){
        cardDeckRepository.save(new CardDeck("A", cardSetRepository.findAll()));
    }

}

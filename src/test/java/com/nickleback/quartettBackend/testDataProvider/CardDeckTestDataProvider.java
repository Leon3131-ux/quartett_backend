package com.nickleback.quartettBackend.testDataProvider;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.CardSet;
import com.nickleback.quartettBackend.repository.CardDeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardDeckTestDataProvider {

    private final CardDeckRepository cardDeckRepository;
    private final CardSetTestDataProvider cardSetTestDataProvider;

    public CardDeck create(){
        CardSet cardSet = cardSetTestDataProvider.create();
        return create("Foo", List.of(cardSet));
    }

    public CardDeck createEmptyCardSets(){
        return create("Foo", new ArrayList<>());
    }

    public CardDeck create(String name, List<CardSet> cardSets){
        return cardDeckRepository.save(new CardDeck(name, cardSets));
    }

}

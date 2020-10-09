package com.nickleback.quartettBackend.testDataProvider;

import com.nickleback.quartettBackend.domain.Card;
import com.nickleback.quartettBackend.domain.CardSet;
import com.nickleback.quartettBackend.repository.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardSetTestDataProvider {

    private final CardSetRepository cardSetRepository;
    private final CardTestDataProvider cardTestDataProvider;

    public CardSet create(String name, List<Card> cards){
        return cardSetRepository.save(new CardSet(name, cards));
    }

    public CardSet create(){
        Card card = cardTestDataProvider.create();
        return create("Foo", List.of(card));
    }

    public CardSet createWithEmptyCards(String name){
        return create(name, new ArrayList<>());
    }

}

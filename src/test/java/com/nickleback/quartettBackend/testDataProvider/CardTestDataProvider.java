package com.nickleback.quartettBackend.testDataProvider;

import com.nickleback.quartettBackend.domain.Card;
import com.nickleback.quartettBackend.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardTestDataProvider {

    private final CardRepository cardRepository;

    public Card create(String name, Long cardIndex, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5){
        return cardRepository.save(new Card(name, cardIndex, attribute1, attribute2, attribute3, attribute4, attribute5, null));
    }

    public Card create(){
       return create("Foo", 1L, "Foo", "Foo", "Foo", "Foo", "Foo");
    }


}

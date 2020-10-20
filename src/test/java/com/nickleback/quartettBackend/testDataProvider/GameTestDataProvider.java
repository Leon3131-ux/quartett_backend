package com.nickleback.quartettBackend.testDataProvider;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.repository.GameRepository;
import com.nickleback.quartettBackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTestDataProvider {

    private final CardDeckTestDataProvider cardDeckTestDataProvider;
    private final GameRepository gameRepository;

    public Game create(String inviteCode, CardDeck cardDeck){
        return gameRepository.save(new Game(inviteCode, null, null, cardDeck));
    }

    public Game create(){
        return create("Foo", cardDeckTestDataProvider.create());
    }

}

package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.repository.GameRepository;
import com.nickleback.quartettBackend.util.InviteCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    @Value("${game.inviteCode.strength}")
    private final Integer inviteCodeStrength;

    private final GameRepository gameRepository;
    private final InviteCodeGenerator inviteCodeGenerator;

    public Game save(Game game){return gameRepository.save(game);}

    public Optional<Game> findByInviteCode(String inviteCode){
        return gameRepository.findByInviteCode(inviteCode);
    }

    public Game createGame(CardDeck cardDeck, String username) {

        String gameInviteCode = inviteCodeGenerator.generateInviteCode(username, inviteCodeStrength);

        return new Game(
                gameInviteCode,
                cardDeck
        );
    }
}

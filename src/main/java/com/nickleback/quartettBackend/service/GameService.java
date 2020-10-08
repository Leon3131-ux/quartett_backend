package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game save(Game game){return gameRepository.save(game);}

    public Optional<Game> findByInviteCode(Long inviteCode){
        return gameRepository.findByInviteCode(inviteCode);
    }

    public Game createGame(CardDeck cardDeck, User user) {

        Long gameInviteCode;
        do {
            gameInviteCode = generateInviteCode().longValue();
        }while (gameRepository.findByInviteCode(gameInviteCode).isPresent());

        return new Game(
                gameInviteCode,
                List.of(user),
                cardDeck
        );
    }

    private Integer generateInviteCode() {
        Random rnd = new Random();
        return (100000 + rnd.nextInt(900000));
    }
}

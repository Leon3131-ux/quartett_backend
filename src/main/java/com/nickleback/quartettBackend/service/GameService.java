package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.*;
import com.nickleback.quartettBackend.repository.GameRepository;
import com.nickleback.quartettBackend.repository.UserRepository;
import com.nickleback.quartettBackend.util.InviteCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    @Value("${game.inviteCode.strength}")
    private Integer inviteCodeStrength;

    @Value("${game.maxTime}")
    private Long maxAllowedTime;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final InviteCodeGenerator inviteCodeGenerator;

    public Game save(Game game){return gameRepository.save(game);}

    public Optional<Game> findByInviteCode(String inviteCode){
        return gameRepository.findByInviteCode(inviteCode);
    }

    public Optional<Game> findById(Long id) {return gameRepository.findById(id);}

    public Game createGame(CardDeck cardDeck, String username) {

        String gameInviteCode = inviteCodeGenerator.generateInviteCode(username, inviteCodeStrength);

        return new Game(
                gameInviteCode,
                null,
                null,
                cardDeck
        );
    }

    public boolean launchGame(Game game){
        int amountOfPlayers = userRepository.findAllByGame_Id(game.getId()).size();
        if(amountOfPlayers >= 2){
            Long now = new Date().getTime();
            game.setStarted(now);
            game.setExpires(now + maxAllowedTime);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    public GameData getGameData(Game game){
        List<User> players = userRepository.findAllByGame_Id(game.getId());
        List<Card> cardStack = game.getCardDeck().getCardSets().stream().flatMap(cardSet -> cardSet.getCards().stream()).collect(Collectors.toList());
        Map<User, PlayerData> playerDataMap = new HashMap<>();

        players.forEach(player -> playerDataMap.put(player, new PlayerData(new ArrayList<>())));

        if(players.size() > 2){
            return getGameDataNoCardStack(cardStack, players, playerDataMap);
        }else{
            return getGameDataWithCardStack(cardStack, players, playerDataMap);
        }
    }

    private GameData getGameDataNoCardStack(List<Card> cards, List<User> players, Map<User, PlayerData> playerDataMap){
        int playerIndex = 0;

        Collections.shuffle(cards);
        for(Card card : cards){
            User player = players.get(playerIndex);

            PlayerData playerData = playerDataMap.get(player);
            playerData.getCards().add(card);
            playerIndex++;

            if(playerIndex == players.size()){
                playerIndex = 0;
            }
        }
        return new GameData(new ArrayList<>(), players, playerDataMap);
    }

    private GameData getGameDataWithCardStack(List<Card> cards, List<User> players, Map<User, PlayerData> playerDataMap){
        Collections.shuffle(cards);
        List<Card> playerCards = new ArrayList<>();
        for(User player : players){
            for(int i = 0; i < 10; i++){
                Card card = cards.get(i);
                PlayerData playerData = playerDataMap.get(player);
                playerData.getCards().add(card);
                playerCards.add(card);
            }
            cards.removeAll(playerCards);
            playerCards = new ArrayList<>();
        }
        return new GameData(cards, players, playerDataMap);
    }


}

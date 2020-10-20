package com.nickleback.quartettBackend.controller;

import com.nickleback.quartettBackend.converter.GameConverter;
import com.nickleback.quartettBackend.domain.*;
import com.nickleback.quartettBackend.dto.GameDto;
import com.nickleback.quartettBackend.service.GameService;
import com.nickleback.quartettBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GameController {


    private final GameService gameService;
    private final UserService userService;
    private final GameConverter gameConverter;
    private final SimpMessageSendingOperations simpleMessagingTemplate;
    private Map<Game, GameData> gameGameDataMap = new HashMap<>();


    @RequestMapping(value = "/api/startGame/with/{cardDeckId}", method = RequestMethod.POST)
    public ResponseEntity<GameDto> startGamePath(@PathVariable("cardDeckId") CardDeck cardDeck, Principal principal){
        if(cardDeck == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        User user = userService.getByUsernameOrThrowException(principal.getName());
        Game createdGame = gameService.createGame(cardDeck, user.getUsername());
        Game savedGame = gameService.save(createdGame);
        user.setGame(savedGame);
        userService.save(user);
        return new ResponseEntity<>(gameConverter.toDto(savedGame), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/joinGame/with/{inviteCode}", method = RequestMethod.POST)
    public ResponseEntity<GameDto> joinGame(@PathVariable("inviteCode") String inviteCode, Principal principal){
        Optional<Game> optionalGame = gameService.findByInviteCode(inviteCode);
        if(optionalGame.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Game game = optionalGame.get();
        User user = userService.getByUsernameOrThrowException(principal.getName());
        user.setGame(game);
        userService.save(user);
        return new ResponseEntity<>(gameConverter.toDto(game), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/launchGame/with/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<?> launchGame(@PathVariable("gameId") Game game){
        if(gameService.launchGame(game)){
            gameGameDataMap.put(game, gameService.getGameData(game));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    private void launchGameOverWebsocket(){

    }

}

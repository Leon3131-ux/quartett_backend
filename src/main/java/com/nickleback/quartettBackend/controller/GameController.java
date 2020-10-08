package com.nickleback.quartettBackend.controller;

import com.nickleback.quartettBackend.converter.GameConverter;
import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.dto.GameDto;
import com.nickleback.quartettBackend.dto.StartGameDto;
import com.nickleback.quartettBackend.service.CardDeckService;
import com.nickleback.quartettBackend.service.GameService;
import com.nickleback.quartettBackend.service.UserService;
import com.nickleback.quartettBackend.validator.StartGameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GameController {


    private final GameService gameService;
    private final GameConverter gameConverter;
    private final StartGameValidator startGameValidator;
    private final UserService userService;
    private final CardDeckService cardDeckService;

    @InitBinder("startGameDto")
    protected void setSingUpDtoInitBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(startGameValidator);
    }

    @RequestMapping(value = "/api/startGame", method = RequestMethod.POST)
    private ResponseEntity startGame(@RequestBody @Validated StartGameDto startGameDto, Principal principal){
        Optional<User> optionalUser = userService.getByUsername(principal.getName());
        Optional<CardDeck> optionalCardDeck = cardDeckService.getById(startGameDto.getCardDeckId());
        List<User> playingUsers = new ArrayList<>();
        if(optionalUser.isPresent()){
            if(optionalCardDeck.isPresent()){
                playingUsers.add(optionalUser.get());
                Game game = new Game(null, playingUsers, optionalCardDeck.get());
                return new ResponseEntity(gameConverter.toDto(gameService.save(game)), HttpStatus.CREATED);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}

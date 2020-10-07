package com.nickleback.quartettBackend.converter;

import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameConverter {

    private final UserConverter userConverter;

    public GameDto toDto(Game game){
        return new GameDto(game.getUuid(), game.getMaxPlayers(), game.getPlayingUsers().stream().map(userConverter::toDto).collect(Collectors.toList()));
    }

}

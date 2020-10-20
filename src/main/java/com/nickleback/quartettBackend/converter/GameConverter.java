package com.nickleback.quartettBackend.converter;

import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.dto.GameDto;
import com.nickleback.quartettBackend.dto.UserDto;
import com.nickleback.quartettBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameConverter {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public GameDto toDto(Game game){
        List<UserDto> users = userRepository.findAllByGame_Id(game.getId()).stream().map(userConverter::toDto).collect(Collectors.toList());
        return new GameDto(game.getId(), game.getInviteCode(), users);
    }

}

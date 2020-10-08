package com.nickleback.quartettBackend.validator;

import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.dto.JoinGameDto;
import com.nickleback.quartettBackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JoinGameValidator implements Validator {

    private final GameService gameService;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinGameDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinGameDto joinGameDto = (JoinGameDto) target;

        Optional<Game> optionalGame = gameService.getById(joinGameDto.getUuid());
        if(optionalGame.isEmpty()){
            errors.reject("errors.startGameDto.uuid.invalid");
        }

    }
}

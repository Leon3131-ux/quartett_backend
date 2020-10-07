package com.nickleback.quartettBackend.validator;

import com.nickleback.quartettBackend.dto.SignUpDto;
import com.nickleback.quartettBackend.dto.StartGameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class StartGameValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return StartGameDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StartGameDto startGameDto = (StartGameDto) target;

        if(startGameDto.getMaxPlayers() == null){
            errors.reject("errors.startGameDto.maxPlayers.empty");
        }
    }
}

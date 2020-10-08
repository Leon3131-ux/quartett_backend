package com.nickleback.quartettBackend.validator;

import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.dto.SignUpDto;
import com.nickleback.quartettBackend.dto.StartGameDto;
import com.nickleback.quartettBackend.service.CardDeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartGameValidator implements Validator {

    private final CardDeckService cardDeckService;

    @Override
    public boolean supports(Class<?> clazz) {
        return StartGameDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StartGameDto startGameDto = (StartGameDto) target;

        Optional<CardDeck> optionalCardDeck = cardDeckService.getById(startGameDto.getCardDeckId());
        if(optionalCardDeck.isEmpty()){
            errors.reject("errors.startGameDto.cardDeckId.invalid");
        }

    }
}

package com.nickleback.quartettBackend.validator;

import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.dto.SignUpDto;
import com.nickleback.quartettBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpDto signUpDto = (SignUpDto) target;

        if(signUpDto.getUsername().isBlank() || signUpDto.getUsername().isEmpty()){
            errors.reject("errors.signUpDto.username.empty");
        }

        if(signUpDto.getPassword().isBlank() || signUpDto.getPassword().isEmpty()){
            errors.reject("errors.signUpDto.password.empty");
        }

        Optional<User> userWithSameName = userService.getByUsername(signUpDto.getUsername());
        if(userWithSameName.isPresent()){
            errors.reject("errors.signUpDto.username.alreadyUsed");
        }

    }
}

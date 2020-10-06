package com.nickleback.boilerplate.validator;

import com.nickleback.boilerplate.domain.User;
import com.nickleback.boilerplate.dto.SignUpDto;
import com.nickleback.boilerplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final UserRepository userRepository;


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

        Optional<User> userWithSameName = userRepository.findByUsername(signUpDto.getUsername());
        if(userWithSameName.isPresent()){
            errors.reject("errors.signUpDto.username.alreadyUsed");
        }

    }
}

package com.nickleback.boilerplate.controller;

import com.nickleback.boilerplate.converter.UserConverter;
import com.nickleback.boilerplate.domain.User;
import com.nickleback.boilerplate.dto.SignUpDto;
import com.nickleback.boilerplate.service.UserService;
import com.nickleback.boilerplate.validator.SignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final SignUpValidator signUpValidator;

    @InitBinder("signUpDto")
    protected void setSingUpDtoInitBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(signUpValidator);
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public ResponseEntity singUpUser(@RequestBody @Validated SignUpDto signUpDto){
        User user = userConverter.singUpDtoToUser(signUpDto);
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}

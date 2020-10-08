package com.nickleback.quartettBackend.controller.web;

import com.nickleback.quartettBackend.converter.UserConverter;
import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.dto.SignUpDto;
import com.nickleback.quartettBackend.security.SecurityConstants;
import com.nickleback.quartettBackend.service.UserService;
import com.nickleback.quartettBackend.validator.SignUpValidator;
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

    @RequestMapping(value = SecurityConstants.SIGN_UP_URL, method = RequestMethod.POST)
    public ResponseEntity<?> singUpUser(@RequestBody @Validated SignUpDto signUpDto){
        User user = userConverter.singUpDtoToUser(signUpDto);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}

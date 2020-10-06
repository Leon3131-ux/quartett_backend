package com.nickleback.boilerplate.converter;

import com.nickleback.boilerplate.domain.Role;
import com.nickleback.boilerplate.domain.User;
import com.nickleback.boilerplate.dto.SignUpDto;
import com.nickleback.boilerplate.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User singUpDtoToUser(SignUpDto signUpDto){
        List<Role> roles = new ArrayList<>();
        Optional<Role> userRole = roleRepository.findByName("USER");
        userRole.ifPresent(roles::add);
        return new User(signUpDto.getUsername(), bCryptPasswordEncoder.encode(signUpDto.getPassword()), roles);
    }

}

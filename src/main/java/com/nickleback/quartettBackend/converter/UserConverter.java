package com.nickleback.quartettBackend.converter;

import com.nickleback.quartettBackend.domain.Role;
import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.dto.SignUpDto;
import com.nickleback.quartettBackend.dto.UserDto;
import com.nickleback.quartettBackend.repository.RoleRepository;
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
        return new User(signUpDto.getUsername(), bCryptPasswordEncoder.encode(signUpDto.getPassword()), roles, null);
    }

    public UserDto toDto(User user){
        return new UserDto(user.getUsername());
    }

}

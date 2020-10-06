package com.nickleback.boilerplate;

import com.nickleback.boilerplate.domain.Permission;
import com.nickleback.boilerplate.domain.Role;
import com.nickleback.boilerplate.domain.User;
import com.nickleback.boilerplate.repository.RoleRepository;
import com.nickleback.boilerplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Init {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void addAdmin(){
        List<Permission> permissions = new ArrayList<>();
        Role testRole = new Role("test", permissions);
        roleRepository.save(testRole);
        List<Role> roles = new ArrayList<>();
        Optional<Role> adminRole = roleRepository.findByName("test");
        adminRole.ifPresent(roles::add);
        this.userService.save(new User("test", bCryptPasswordEncoder.encode("test"), roles));
    }
}

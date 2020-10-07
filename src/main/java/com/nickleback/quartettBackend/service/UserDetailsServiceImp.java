package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        }else{
            throw new UsernameNotFoundException(username);
        }

    }

    private List<SimpleGrantedAuthority> getAuthorities(User user){
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role -> role.getPermissions()
                .forEach(permission -> grantedAuthorities
                        .add(new SimpleGrantedAuthority(permission.getName().toString()))));
        return grantedAuthorities;
    }

}

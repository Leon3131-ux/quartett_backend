package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {return userRepository.save(user);}

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getByUsernameOrThrowException(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}

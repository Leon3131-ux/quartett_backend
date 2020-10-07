package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.User;
import com.nickleback.quartettBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

}

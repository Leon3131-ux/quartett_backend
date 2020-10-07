package com.nickleback.quartettBackend.service;

import com.nickleback.quartettBackend.domain.Game;
import com.nickleback.quartettBackend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService extends BaseService<Game, UUID> {


    public GameService(GameRepository repository) {
        super(repository);
    }
}

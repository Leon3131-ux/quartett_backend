package com.nickleback.quartettBackend.repository;

import com.nickleback.quartettBackend.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByInviteCode(String gameInviteCode);
}

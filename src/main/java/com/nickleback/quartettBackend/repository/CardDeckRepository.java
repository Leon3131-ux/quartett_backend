package com.nickleback.quartettBackend.repository;

import com.nickleback.quartettBackend.domain.CardDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDeckRepository extends JpaRepository<CardDeck, Long> {
}

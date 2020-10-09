package com.nickleback.quartettBackend.repository;

import com.nickleback.quartettBackend.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

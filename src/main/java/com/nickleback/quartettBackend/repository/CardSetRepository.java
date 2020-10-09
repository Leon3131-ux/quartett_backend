package com.nickleback.quartettBackend.repository;

import com.nickleback.quartettBackend.domain.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
}

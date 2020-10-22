package com.nickleback.quartettBackend.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDeck extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "cardDeck")
    private List<CardSet> cardSets;
}

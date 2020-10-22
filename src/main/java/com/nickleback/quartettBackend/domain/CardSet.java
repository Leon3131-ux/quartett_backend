package com.nickleback.quartettBackend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardSet extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "cardSet")
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name="cardDeck_id", nullable=false)
    private CardDeck cardDeck;
}

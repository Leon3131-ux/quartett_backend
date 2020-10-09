package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String inviteCode;

    @OneToOne
    private CardDeck cardDeck;

}

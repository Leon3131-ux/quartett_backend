package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private Long inviteCode;

    @OneToMany
    private List<User> playingUsers;

    @OneToOne
    private CardDeck cardDeck;

    public void addPlayingUser(User user){
        playingUsers.add(user);
    }
}

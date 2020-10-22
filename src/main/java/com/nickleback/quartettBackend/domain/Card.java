package com.nickleback.quartettBackend.domain;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long cardIndex;

    @Column(nullable = false)
    private String attribute1;

    @Column(nullable = false)
    private String attribute2;

    @Column(nullable = false)
    private String attribute3;

    @Column(nullable = false)
    private String attribute4;

    @Column(nullable = false)
    private String attribute5;

    @OneToOne
    private CardImage cardImage;

    @ManyToOne
    @JoinColumn(name="cardSet_id", nullable=false)
    private CardSet cardSet;
}

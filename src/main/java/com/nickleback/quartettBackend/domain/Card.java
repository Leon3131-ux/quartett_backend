package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

}

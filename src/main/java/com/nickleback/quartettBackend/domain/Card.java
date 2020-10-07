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

    @Column
    private String name;

    @Column
    private Long cardIndex;

    @Column
    private String attribute1;

    @Column
    private String attribute2;

    @Column
    private String attribute3;

    @Column
    private String attribute4;

    @Column
    private String attribute5;

    @OneToOne
    private CardImage cardImage;

}

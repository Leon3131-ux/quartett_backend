package com.nickleback.quartettBackend.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AbstractEntity(){
        this.id = null;
    }

    public AbstractEntity(long id){
        this.id = id;
    }

}

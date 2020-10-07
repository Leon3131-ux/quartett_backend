package com.nickleback.quartettBackend.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Permission extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;
}

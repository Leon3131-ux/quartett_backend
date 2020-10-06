package com.nickleback.boilerplate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Permission extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;
}

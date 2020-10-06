package com.nickleback.boilerplate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private List<Permission> permissions;

}

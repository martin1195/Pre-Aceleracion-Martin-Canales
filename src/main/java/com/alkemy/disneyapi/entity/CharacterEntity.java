package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50)
    private String name;
    private String image;
    private Short age;
    private Float weight;
    private String history;
    @ManyToMany(mappedBy = "characters" , cascade = CascadeType.ALL)
    private Set<MovieEntity> movies;
}

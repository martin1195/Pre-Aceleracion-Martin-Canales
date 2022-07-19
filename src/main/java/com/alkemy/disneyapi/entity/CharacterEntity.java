package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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
    private Set<MovieEntity> movies = new HashSet<>();
    private Boolean deleted = Boolean.FALSE;
}

package com.alkemy.disneyapi.dto;

import com.alkemy.disneyapi.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    private String name;
    private String image;
    private Short age;
    private Float weight;
    private String history;
    private Set<MovieDTO> movies;
}

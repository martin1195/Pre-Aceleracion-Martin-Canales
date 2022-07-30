package com.alkemy.disneyapi.dto;

import com.alkemy.disneyapi.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    private Short age;
    private Float weight;
    private String history;
    private Set<MovieDTO> movies;
}

package com.alkemy.disneyapi.dto;

import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Short rating;
    private GenreDTO genre;
    private Set<CharacterDTO> characters = new HashSet<>();
}

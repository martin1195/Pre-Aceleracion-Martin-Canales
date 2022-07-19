package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {
    private String name;
    private Short age;
    private List<Long> movies;

    public CharacterFiltersDTO(String name,Short age,List<Long> movies){
        this.name=name;
        this.age=age;
        this.movies=movies;
    }
}

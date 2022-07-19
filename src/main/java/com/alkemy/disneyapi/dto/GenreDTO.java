package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class GenreDTO {
    private Long id;
    private String name;
    private String image;
}

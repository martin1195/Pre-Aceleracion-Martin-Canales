package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MovieBasicDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
}

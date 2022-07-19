package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto){
        GenreEntity  genreEntity = new GenreEntity();
        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }
}

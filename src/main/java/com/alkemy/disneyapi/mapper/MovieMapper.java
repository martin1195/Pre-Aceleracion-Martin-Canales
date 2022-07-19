package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.GenreEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterMapper characterMapper;
    public MovieEntity movieDTO2Entity(MovieDTO dto){
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setCreationDate(dto.getCreationDate());
        GenreEntity genre = genreMapper.genreDTO2Entity(dto.getGenre());
        entity.setGenre(genre);
        entity.setRating(dto.getRating());
        entity.setTitle(dto.getTitle());
        Set<CharacterEntity> characters = characterMapper.characterDTOSet2EntitySet(dto.getCharacters());
        entity.setCharacters(characters);
        return entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        GenreDTO genre = genreMapper.genreEntity2DTO(entity.getGenre());
        dto.setGenre(genre);
        dto.setRating(entity.getRating());
        dto.setCreationDate(entity.getCreationDate());
        dto.setTitle(entity.getTitle());
        if (loadCharacters){
            Set<CharacterDTO> charactersDTO = characterMapper.characterEntitySet2DTOSet(entity.getCharacters(),false);
            dto.setCharacters(charactersDTO);
        }
        return dto;
    }
    public Set<MovieDTO> movieEntitySet2DTOSet(Set<MovieEntity> entities,boolean loadCharacters){
        Set<MovieDTO> dtoSet = new HashSet<>();
        for (MovieEntity entity : entities){
            dtoSet.add(this.movieEntity2DTO(entity,loadCharacters));
        }
        return dtoSet;
    }

    public Set<MovieEntity> movieDTOSet2EntitySet(Set<MovieDTO> dtoSet){
        Set<MovieEntity> entities = new HashSet<>();
        for (MovieDTO dto : dtoSet){
            entities.add(this.movieDTO2Entity(dto));
        }
        return entities;
    }
    public Set<MovieBasicDTO> movieEntityList2DTOBasicSet(List<MovieEntity> entities){
        Set<MovieBasicDTO> basicDTOS = new HashSet<>();
        MovieBasicDTO basicDTO= new MovieBasicDTO();
        for (MovieEntity entity : entities){
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setCreationDate(entity.getCreationDate());
            basicDTO.setTitle(entity.getTitle());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }
}

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterMapper characterMapper;
    public MovieEntity movieDTO2Entity(MovieDTO dto, boolean loadCharacters){
        MovieEntity entity = new MovieEntity();
        convertValues(dto,entity);
        GenreEntity genre = genreMapper.genreDTO2Entity(dto.getGenre());
        entity.setGenre(genre);
        if (loadCharacters){
            Set<CharacterEntity> characters = characterMapper.characterDTOSet2EntitySet(dto.getCharacters(),false);
            entity.setCharacters(characters);
        }
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

    public Set<MovieEntity> movieDTOSet2EntitySet(Set<MovieDTO> dtoSet,boolean loadCharacters){
        Set<MovieEntity> entities = new HashSet<>();
        for (MovieDTO dto : dtoSet){
            entities.add(this.movieDTO2Entity(dto,loadCharacters));
        }
        return entities;
    }
    public List<MovieBasicDTO> movieEntityList2DTOBasicList(List<MovieEntity> entities){
        List<MovieBasicDTO> basicDTOS = new ArrayList<>();

        for (MovieEntity entity : entities){
            MovieBasicDTO basicDTO= new MovieBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setCreationDate(entity.getCreationDate());
            basicDTO.setTitle(entity.getTitle());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }
    public void convertValues(MovieDTO movieDTO,MovieEntity movieEntity){
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setCreationDate(movieDTO.getCreationDate());
        movieEntity.setRating(movieDTO.getRating());
        movieEntity.setTitle(movieDTO.getTitle());
    }
    public MovieEntity replaceAttributes(MovieEntity movie, MovieDTO movieDTO){
        convertValues(movieDTO,movie);
        return movie;
    }
}

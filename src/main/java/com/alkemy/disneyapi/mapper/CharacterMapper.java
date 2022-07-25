package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;
    public CharacterEntity characterDTO2Entity(CharacterDTO dto,boolean loadMovies){
        CharacterEntity entity = new CharacterEntity();
        convertValues(dto,entity);
        if (loadMovies){
            Set<MovieEntity> movies = movieMapper.movieDTOSet2EntitySet(dto.getMovies(),false);
            entity.setMovies(movies);
        }
        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity,boolean loadMovies){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setHistory(entity.getHistory());
        dto.setWeight(entity.getWeight());
        if (loadMovies){
            Set<MovieDTO> movieDTOS = movieMapper.movieEntitySet2DTOSet(entity.getMovies(),false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

    public Set<CharacterBasicDTO> characterEntityList2BasicDTOSet(Collection<CharacterEntity> entities){
        Set<CharacterBasicDTO> dtos = new HashSet<>();
        CharacterBasicDTO basicDTO;
        for(CharacterEntity entity : entities){
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setName(entity.getName());
            basicDTO.setImage(entity.getImage());
            dtos.add(basicDTO);
        }
        return dtos;
    }
    public Set<CharacterDTO> characterEntitySet2DTOSet(Set<CharacterEntity> entities, boolean loadMovies){
        Set<CharacterDTO> dtoSet = new HashSet<>();
        for (CharacterEntity entity : entities){
            dtoSet.add(this.characterEntity2DTO(entity,loadMovies));
        }
        return dtoSet;
    }
    public Set<CharacterEntity> characterDTOSet2EntitySet(Set<CharacterDTO> dtoSet,boolean loadMovies){
        Set<CharacterEntity> entities = new HashSet<>();
        for (CharacterDTO dto : dtoSet){
            entities.add(this.characterDTO2Entity(dto,loadMovies));
        }
        return entities;
    }
    public void convertValues(CharacterDTO characterDTO,CharacterEntity characterEntity){
        characterEntity.setName(characterDTO.getName());
        characterEntity.setImage(characterDTO.getImage());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setHistory(characterDTO.getHistory());
        characterEntity.setWeight(characterDTO.getWeight());
    }
    public CharacterEntity replaceAttributes(CharacterEntity characterEntity,CharacterDTO characterDTO){
        convertValues(characterDTO,characterEntity);
        return characterEntity;
    }
}

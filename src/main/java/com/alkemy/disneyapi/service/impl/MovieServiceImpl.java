package com.alkemy.disneyapi.service.impl;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.exception.ParamNotFound;
import com.alkemy.disneyapi.mapper.MovieMapper;
import com.alkemy.disneyapi.repository.MovieRepository;
import com.alkemy.disneyapi.repository.specifications.MovieSpecification;
import com.alkemy.disneyapi.service.CharacterService;
import com.alkemy.disneyapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    private CharacterService characterService;
    public MovieDTO save(MovieDTO dto){
        MovieEntity entity = movieMapper.movieDTO2Entity(dto,true);
        MovieEntity saved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(saved,true);
        return result;
    }

    public MovieDTO getOne(Long id){
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        MovieEntity entity = new MovieEntity();
        if (!movieEntityOptional.isPresent()){
            throw new ParamNotFound("Not found Movie with id = " + id);
        }
        entity = movieEntityOptional.get();
        MovieDTO dto = movieMapper.movieEntity2DTO(entity,true);
        return dto;
    }
    public List<MovieBasicDTO> getByFilters(String title,Long idGenre,String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title,idGenre,order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> movieBasicDTOS = movieMapper.movieEntityList2DTOBasicList(entities);
        return movieBasicDTOS;
    }
    public  void  addCharacter(Long id,Long idCharacter){
        MovieEntity movie = movieRepository.getReferenceById(id);
        CharacterEntity character = characterService.getCharacterById(idCharacter);
        if(movie == null){
            throw new ParamNotFound("Not found Movie with id = " + id);
        } else if (character == null) {
            throw new ParamNotFound("Not found Character with id = " + id);
        }
        movie.addCharacter(character);
        movieRepository.save(movie);
    }

    public MovieDTO update(Long id, MovieDTO movie) {
        MovieEntity movieModified = movieRepository.save(movieMapper.replaceAttributes(movieRepository.getReferenceById(id),movie));
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(movieModified,true);
        return movieDTO;
    }

    public void removeCharacter(Long id, Long idCharacter) {
        MovieEntity movie = movieRepository.getReferenceById(id);
        CharacterEntity character = characterService.getCharacterById(idCharacter);
        movie.removeCharacter(character);
        movieRepository.save(movie);
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }
}

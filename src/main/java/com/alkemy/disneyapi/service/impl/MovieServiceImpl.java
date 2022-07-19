package com.alkemy.disneyapi.service.impl;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
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
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity saved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(saved,true);
        return result;
    }

    public MovieDTO getOne(Long id){
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        MovieEntity entity = new MovieEntity();
        if (movieEntityOptional.isPresent()){
            entity = movieEntityOptional.get();
        }
        MovieDTO dto = movieMapper.movieEntity2DTO(entity,true);
        return dto;
    }
    public Set<MovieBasicDTO> getAll(){
        List<MovieEntity> entities = movieRepository.findAll();
        Set<MovieBasicDTO> basicDTOS = movieMapper.movieEntityList2DTOBasicSet(entities);
        return basicDTOS;
    }
    public Set<MovieBasicDTO> getByFilters(String title,Long idGenre,String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title,idGenre,order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        Set<MovieBasicDTO> movieBasicDTOS = movieMapper.movieEntityList2DTOBasicSet(entities);
        return movieBasicDTOS;
    }
    public  void  addCharacter(Long id,Long idCharacter){
        MovieEntity movie = movieRepository.getReferenceById(id);
        CharacterEntity character = characterService.getCharacterById(idCharacter);
        movie.addCharacter(character);
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

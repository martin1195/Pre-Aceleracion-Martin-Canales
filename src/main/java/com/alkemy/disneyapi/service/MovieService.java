package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;

import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    MovieDTO getOne(Long id);
    Set<MovieBasicDTO> getAll();
    void delete(Long id);
    Set<MovieBasicDTO> getByFilters(String title,Long idGenre,String order);
    void removeCharacter(Long id,Long idCharacter);
    void  addCharacter(Long id,Long idCharacter);
}

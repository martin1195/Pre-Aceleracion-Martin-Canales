package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    MovieDTO getOne(Long id);
    void delete(Long id);
    List<MovieBasicDTO> getByFilters(String title, Long idGenre, String order);
    void removeCharacter(Long id,Long idCharacter);
    void  addCharacter(Long id,Long idCharacter);
    MovieDTO update(Long id,MovieDTO movie);
}

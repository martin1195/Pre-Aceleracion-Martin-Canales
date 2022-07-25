package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);
    CharacterDTO getOne(Long id);
    CharacterEntity getCharacterById(Long id);
    void delete(Long id);
    Set<CharacterBasicDTO> getByFilters(String name, Short age, List<Long> movies);
    CharacterDTO update(Long id,CharacterDTO characterDTO);
}

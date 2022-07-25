package com.alkemy.disneyapi.service.impl;

import com.alkemy.disneyapi.dto.*;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.mapper.CharacterMapper;
import com.alkemy.disneyapi.repository.CharacterRepository;
import com.alkemy.disneyapi.repository.specifications.CharacterSpecification;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    CharacterSpecification characterSpecification;
    public CharacterDTO save(CharacterDTO dto){
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto,false);
        CharacterEntity saved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(saved,false);
        return result;
    }
    public CharacterEntity getCharacterById(Long id){
        CharacterEntity entity = characterRepository.getReferenceById(id);
        return  entity;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    public Set<CharacterBasicDTO> getByFilters(String name, Short age, List<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name,age,movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        Set<CharacterBasicDTO> characterBasicDTOS = characterMapper.characterEntityList2BasicDTOSet(entities);
        return characterBasicDTOS;
    }

    public CharacterDTO update(Long id, CharacterDTO characterDTO) {
        CharacterEntity characterModified = characterMapper.replaceAttributes(characterRepository.getReferenceById(id),characterDTO);
        return characterMapper.characterEntity2DTO(characterRepository.save(characterModified),true);
    }

    public CharacterDTO getOne(Long id){
        CharacterEntity entity = new CharacterEntity();
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()){
            entity = result.get();
        }
        CharacterDTO dto = characterMapper.characterEntity2DTO(entity,true);
        return dto;
    }
}

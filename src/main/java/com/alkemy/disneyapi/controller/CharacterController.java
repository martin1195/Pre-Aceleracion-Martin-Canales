package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character){
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getOne(@PathVariable Long id){
        CharacterDTO characterDTO = characterService.getOne(id);
        return ResponseEntity.ok(characterDTO);
    }
    @GetMapping
    public ResponseEntity<Set<CharacterBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Short age,
            @RequestParam(required = false) List<Long> movies
    ){
        Set<CharacterBasicDTO> characters = characterService.getByFilters(name,age,movies);
        return ResponseEntity.ok(characters);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id,@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok().body(characterService.update(id,characterDTO));
    }
}

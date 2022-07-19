package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Set<MovieBasicDTO>> getAll(){
        Set<MovieBasicDTO> basicDTOSet = movieService.getAll();
        return ResponseEntity.ok(basicDTOSet);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getOne(@PathVariable Long id){
        MovieDTO movieDTO = movieService.getOne(id);
        return ResponseEntity.ok(movieDTO);
    }
    @GetMapping
    public ResponseEntity<Set<MovieBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        Set<MovieBasicDTO> movies = movieService.getByFilters(title,idGenre,order);
        return ResponseEntity.ok(movies);
    }
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie){
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> remove(@PathVariable Long id, @PathVariable Long idCharacter){
        movieService.removeCharacter(id,idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter){
        movieService.addCharacter(id,idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

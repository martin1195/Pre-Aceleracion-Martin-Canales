package com.alkemy.disneyapi.repository;

import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {
    public List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}

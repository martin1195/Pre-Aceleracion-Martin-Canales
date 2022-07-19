package com.alkemy.disneyapi.repository.specifications;

import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if (filtersDTO.getAge() > 0){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("genre_id")),
                                "%" + filtersDTO.getAge() + "%"
                        )
                );
            }
            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> movie_id = join.get("id");
                predicates.add(movie_id.in(filtersDTO.getMovies()));
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

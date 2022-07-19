package com.alkemy.disneyapi.repository.specifications;

import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            if (filtersDTO.getIdGenre() > 0){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("genre_id")),
                                "%" + filtersDTO.getIdGenre() + "%"
                        )
                );
            }
            query.distinct(true);
            String orderByField = "creation_date";
            query.orderBy(
                    filtersDTO.isDESC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

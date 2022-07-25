package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50)
    private String name;
    private String image;
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<MovieEntity> movies = new HashSet<>();
    private Boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        if (this.getId()!=null && this.getName()!=null){
            return this.getId().equals(that.getId())&&this.getName().equals(that.getName());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image);
    }
}

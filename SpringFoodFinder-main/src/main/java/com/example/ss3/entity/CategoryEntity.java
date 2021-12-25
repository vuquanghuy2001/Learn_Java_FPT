package com.example.ss3.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    public CategoryEntity(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<DishEntity> dishes;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<RecipeEntity> recipes;



}

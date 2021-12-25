package com.example.ss3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private Integer category_id;

    @ManyToOne() //EAGER
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<DishEntity> dishes;

    public RecipeEntity(String name, String description, Integer category_id) {
        this.name = name;
        this.description = description;
        this.category_id = category_id;
    }
}

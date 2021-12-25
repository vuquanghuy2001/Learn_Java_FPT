package com.example.ss3.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "recipe_id")
    private Integer recipe_id;


    @Column(name = "image_url")
    private String image_url;


    @Column(name = "method")
    private String method;

    @Column(name = "ingredient_des")
    private String ingredient_des;

    @Column(name = "price",columnDefinition = "integer default 100")
    private Float price;

    @ManyToOne() //EAGER
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @ManyToOne() //EAGER
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private RecipeEntity recipe;

    @JsonIgnore
    @OneToMany(mappedBy = "dish")
    public  Collection<ItemEntity> orders;

    @ManyToMany(mappedBy = "dishes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    private Collection<IngredientEntity> ingredients;

    public DishEntity(String name, Integer category, Integer recipe, String image, String method, String ingredient_des) {
        this.name = name;
        this.category_id = category;
        this.recipe_id = recipe;
        this.image_url = image;
        this.method = method;
        this.ingredient_des = ingredient_des;
    }
}

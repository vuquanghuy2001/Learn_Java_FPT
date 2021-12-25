package com.example.ss3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "ingredient")
public class IngredientEntity {

    public IngredientEntity() {
    }

    public IngredientEntity(Integer id, String name, String image, Collection<DishEntity> dishes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.dishes = dishes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "ingredient_enrol", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "ingredient_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại
            inverseJoinColumns = @JoinColumn(name = "dish_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
    )
    private Collection<DishEntity> dishes;

    public IngredientEntity(String name, String image) {
        this.name = name;
        this.image = image;
    }
}

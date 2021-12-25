package com.example.ss3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "cart_id")
    private Integer cart_id;


    @JsonIgnore
    @ManyToOne() //EAGER
    @JoinColumn(name = "cart_id",insertable = false, updatable = false)
    private CartEntity cart;

//    @JsonIgnore - get
    @ManyToOne() //EAGER
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private DishEntity dish;

    public ItemEntity(Integer quantity, Integer product_id, Integer cart_id) {
        this.quantity = quantity;
        this.product_id = product_id;
        this.cart_id = cart_id;
    }
}

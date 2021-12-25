package com.example.ss3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "comment")
    private String comment;


    @Column(name = "status_id",columnDefinition = "integer default 1")
    private Integer status_id;


    @JsonIgnore
    @ManyToOne() //EAGER
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private UserEntity user;


    @JsonIgnore
    @ManyToOne() //EAGER
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
    public Collection<ItemEntity> items;

    public CartEntity(Integer user_id, String phone, String address, String comment, Integer status_id) {
        this.user_id = user_id;
        this.phone = phone;
        this.address = address;
        this.comment = comment;
        this.status_id = status_id;
    }
}

package com.example.ss3.repository;

import com.example.ss3.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CartRepo extends JpaRepository<CartEntity, Integer> {
    @Query(value = "SELECT c FROM CartEntity c  WHERE c.user.id=:id")
    public Page<CartEntity> findCartByUser(@Param("id") Integer id, Pageable pageable);

    @Query(value = "SELECT c FROM CartEntity c  WHERE c.user.id=:id")
    public List<CartEntity> findALlCartByUser(@Param("id") Integer id);

    @Query(value = "SELECT c FROM CartEntity c  WHERE c.status_id=:id")
    public  Page<CartEntity> findByStatus_id(Integer id, Pageable pageable);





}

package com.example.demo.response;

import com.example.demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleReponsitory extends JpaRepository<Sale, Integer> {
}

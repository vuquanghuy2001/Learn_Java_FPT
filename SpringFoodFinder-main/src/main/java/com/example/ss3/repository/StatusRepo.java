package com.example.ss3.repository;

import com.example.ss3.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<StatusEntity, Integer> {
}

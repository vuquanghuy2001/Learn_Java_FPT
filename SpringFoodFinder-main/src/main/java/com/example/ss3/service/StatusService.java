package com.example.ss3.service;

import com.example.ss3.dto.CategoryDto;
import com.example.ss3.dto.StatusDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.StatusEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StatusService {
    List<StatusEntity> getAll();
    Page<StatusEntity> findPaginated(int pageNo, int pageSize);
    StatusEntity findByID(Integer id);
    void  add(StatusDto statusDto);
    StatusEntity save(Integer id,String name);
    void delete(Integer id);
}

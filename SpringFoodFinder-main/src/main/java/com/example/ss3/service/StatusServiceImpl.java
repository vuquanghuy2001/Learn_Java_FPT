package com.example.ss3.service;

import com.example.ss3.dto.StatusDto;
import com.example.ss3.entity.StatusEntity;
import com.example.ss3.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements  StatusService{
    @Autowired
    StatusRepo statusRepo;

    @Override
    public List<StatusEntity> getAll() {
        return statusRepo.findAll();
    }

    @Override
    public Page<StatusEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.statusRepo.findAll(pageable);
    }

    @Override
    public StatusEntity findByID(Integer id) {
        return statusRepo.findById(id).get();
    }

    @Override
    public void add(StatusDto statusDto) {
        StatusEntity statusEntity = new StatusEntity(
            statusDto.getName()
        );
        statusRepo.save(statusEntity);
    }

    @Override
    public StatusEntity save(Integer id, String name) {
        StatusEntity statusEntity = findByID(id);
        statusEntity.setName(name);
        return statusRepo.save(statusEntity);
    }

    @Override
    public void delete(Integer id) {
        StatusEntity statusEntity = findByID(id);
        statusRepo.delete(statusEntity);
    }
}

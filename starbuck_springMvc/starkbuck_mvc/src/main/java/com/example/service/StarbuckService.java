package starkbuck_mvc.src.main.java.com.example.service;

import starkbuck_mvc.src.main.java.com.example.models.starbuck;

import java.util.List;

public interface StarbuckService {
    public List<starbuck> findAll();

    public starbuck findById(int id);

    public void save(starbuck theStarbuck);

    public void deleteById(int theId);
}

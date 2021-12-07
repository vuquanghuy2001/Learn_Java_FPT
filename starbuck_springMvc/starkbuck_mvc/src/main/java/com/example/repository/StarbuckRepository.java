package starkbuck_mvc.src.main.java.com.example.repository;

import starkbuck_mvc.src.main.java.com.example.models.starbuck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StarbuckRepository extends JpaRepository<starbuck, Integer> {
}

package backend.artise.repos;

import backend.artise.dto.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    List<Category> getAllByProfessionId(int id);

}
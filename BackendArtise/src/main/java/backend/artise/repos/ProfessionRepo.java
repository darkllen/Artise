package backend.artise.repos;

import backend.artise.dto.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionRepo extends JpaRepository<Profession, Integer> {

    List<Profession> findAll();

}
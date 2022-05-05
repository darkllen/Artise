package backend.artise.repos;

import backend.artise.dto.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceRepo extends JpaRepository<Service, Integer> {

    @Query("SELECT s FROM Service s WHERE s.userCategory.category = ?1")
    List<Service> findAllByCategory(Category category, Sort sort);

    @Query("SELECT s FROM Service s WHERE s.userCategory.category.profession = ?1")
    List<Service> findAllByProfession(Profession profession, Sort sort);


    @Query("SELECT s FROM Service s  WHERE s.userCategory.user = ?1")
    List<Service> findAllByUser(User user, Sort sort);

    List<Service> findAll(Sort sort);

}

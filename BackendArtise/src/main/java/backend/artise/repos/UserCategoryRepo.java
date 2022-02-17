package backend.artise.repos;

import backend.artise.dto.Category;
import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCategoryRepo extends JpaRepository<UserCategory, Integer> {
    List<UserCategory> getAllByUserId(int id);

    List<UserCategory> getAllByCategoryId(int id);

    List<UserCategory> getAllByUserIdAndCategoryId(int userId, int catId);



}

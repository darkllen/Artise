package backend.artise.repos;

import backend.artise.dto.Connection;
import backend.artise.dto.Review;
import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findAllByUserCategory(UserCategory category, Sort sort);

    List<Review> findAllByUser(User user, Sort sort);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.userCategory = ?1")
    Float getAverageRating(UserCategory category);


    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.user = ?1")
    Float getAvgRatingUser(User user);
}

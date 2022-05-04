package backend.artise.repos;

import backend.artise.dto.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> getByNickname(String nick);

    @Query("SELECT u from User u where u <> ?1")
    List<User> getUsersListWithoutCurrent(User userInit, Sort by);
}

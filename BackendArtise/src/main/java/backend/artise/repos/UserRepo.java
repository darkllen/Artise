package backend.artise.repos;

import backend.artise.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getByNickname(String nick);
}

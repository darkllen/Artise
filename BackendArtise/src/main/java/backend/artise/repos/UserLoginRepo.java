package backend.artise.repos;

import backend.artise.dto.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepo extends JpaRepository<UserLogin, Integer> {
    Optional<UserLogin> findUserByNickname(String login);
}
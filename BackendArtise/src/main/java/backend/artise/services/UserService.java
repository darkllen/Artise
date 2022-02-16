package backend.artise.services;

import backend.artise.dto.User;
import backend.artise.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User getByNickname(String nick){
        return userRepo.getByNickname(nick);
    }

    public User addUser(User user) {
        userRepo.saveAndFlush(user);
        return user;
    }
}

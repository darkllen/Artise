package backend.artise.services;

import backend.artise.dto.Category;
import backend.artise.dto.User;
import backend.artise.dto.UserLogin;
import backend.artise.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getByNickname(String nick){
        return userRepo.getByNickname(nick);
    }

    public User addUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.saveAndFlush(user);
        return user;
    }

    public User getUserByLogin(UserLogin userLogin){
        Optional<User> user = userRepo.getByNickname(userLogin.getNickname());
        if (user.isEmpty()) return null;
        if (user.get().getPassword().equals(userLogin.getPassword())) return user.get();
        return null;
    }

    public User editUser(User user) {
        userRepo.saveAndFlush(user);
        return user;
    }

    public Optional<User> getById(int id){
        return userRepo.findById(id);
    }

}

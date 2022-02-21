package backend.artise.services;

import backend.artise.dto.UserLogin;
import backend.artise.repos.UserLoginRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService implements UserDetailsService {

    private final UserLoginRepo repo;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserLogin user = repo.findUserByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

}
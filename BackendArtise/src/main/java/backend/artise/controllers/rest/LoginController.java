package backend.artise.controllers.rest;

import backend.artise.dto.User;
import backend.artise.dto.UserLogin;
import backend.artise.security.JwtProvider;
import backend.artise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

//    private final UserViewMapper userViewMapper;



    @ResponseBody
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody @Valid User user){
        if (userService.getByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("nick is already exists");
        }
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok().header(
                HttpHeaders.AUTHORIZATION,
                jwtTokenUtil.generateToken(createdUser.getEmail(), String.valueOf(createdUser.getId()))
        ).body(createdUser);
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLogin request) {
        try {
            System.out.println(request);
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(), request.getPassword()
                            )
                    );

            System.out.println("asa");
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
            User user1 = userService.getByEmail(request.getEmail()).get();
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user.getUsername(), String.valueOf(user1.getId()))
                    )
                    .body(user1);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

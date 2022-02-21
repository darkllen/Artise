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
        if (userService.getByNickname(user.getNickname()).isPresent()){
            return ResponseEntity.badRequest().body("nick is already exists");
        }
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLogin request) {
        try {
            System.out.println(request);
//            request.setPassword(passwordEncoder.encode(request.getPassword()));
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getNickname(), request.getPassword()
                            )
                    );

            System.out.println("asa");
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
            System.out.println(user.getUsername());
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user.getUsername())
                    )
                    .body(userService.getByNickname(user.getUsername()).get());
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

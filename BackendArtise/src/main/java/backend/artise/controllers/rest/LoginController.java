package backend.artise.controllers.rest;

import backend.artise.dto.User;
import backend.artise.dto.UserLogin;
import backend.artise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @ResponseBody
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody @Valid User user){
        if (userService.getByNickname(user.getNickname()) != null){
            return ResponseEntity.badRequest().body("nick is already exists");
        }
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @ResponseBody
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid UserLogin user){
        User userRes = userService.getUserByLogin(user);
        if (userRes == null) return ResponseEntity.badRequest().body("wrong password or nick");
        return ResponseEntity.ok(userRes);
    }

}

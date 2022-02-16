package backend.artise.controllers;

import backend.artise.dto.User;
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
}

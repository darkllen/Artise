package backend.artise.controllers.rest;

import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import backend.artise.dto.UserLogin;
import backend.artise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RestUserController {
    private final UserService service;

    @ResponseBody
    @RequestMapping(value = "/edit_user", method = RequestMethod.PUT)
    public User editUser(@RequestBody @Valid User user){
        //todo exception if id is wrong
        //todo user authorization
        return service.editUser(user);
    }


    @RequestMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable(value="id") Integer id) {
        Optional<User> userOptional = service.getById(id);
        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("wrong user id");
        }
    }
}

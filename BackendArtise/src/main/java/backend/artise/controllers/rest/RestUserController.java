package backend.artise.controllers.rest;

import backend.artise.dto.Connection;
import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import backend.artise.dto.UserLogin;
import backend.artise.services.ConnectionService;
import backend.artise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
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

    @RequestMapping(value = "/get_users_list", method = RequestMethod.GET)
    public ResponseEntity getUsersList(@RequestBody Map<String, Object> map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Optional<User> userInit = service.getByNickname(principal.getName());
        return ResponseEntity.ok().body(service.getUsersListWithoutCurrent(userInit.get(), sort_by, order));
    }




}

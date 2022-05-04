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
public class RestConnectionsController {
    private final UserService userService;
    private final ConnectionService connectionService;

    @RequestMapping(value = "/create_connection", method = RequestMethod.POST)
    public ResponseEntity createConnection(@RequestBody Map<String, Integer> map, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        Optional<User> userConnect = userService.getById(map.get("user_id"));
        if (userConnect.isEmpty()){
            return ResponseEntity.badRequest().body("wrong user to connect");
        }
        if (userInit.get().getId().equals(userConnect.get().getId())){
            return ResponseEntity.badRequest().body("user is same as auth");
        }
        if (connectionService.getByIds(userInit.get(), userConnect.get()) != null){
            return ResponseEntity.badRequest().body("connect exists");
        }
        return ResponseEntity.ok().body(connectionService.createConnection(userInit.get(), userConnect.get()));
    }

    @RequestMapping(value = "/approve_connection", method = RequestMethod.POST)
    public ResponseEntity approveConnection(@RequestBody Map<String, Integer> map, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());

        try{
            Connection connection = connectionService.getById(map.get("connection_id"));
            if (!connection.getUser_to_connect().getId().equals(userInit.get().getId())){
                return ResponseEntity.badRequest().body("this user can not approve this connection");
            }
            if (connection.getStatus().equals(1)){
                return ResponseEntity.badRequest().body("connection already approved");
            }
            connectionService.approve(connection);
            return ResponseEntity.ok().body("approved");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("wrong connection id");
        }

    }

    @RequestMapping(value = "/decline_connection", method = RequestMethod.POST)
    public ResponseEntity declineConnection(@RequestBody Map<String, Integer> map, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());

        try{
            Connection connection = connectionService.getById(map.get("connection_id"));
            if (!connection.getUser_to_connect().getId().equals(userInit.get().getId()) && !connection.getUser_init().getId().equals(userInit.get().getId())){
                return ResponseEntity.badRequest().body("this user can not decline this connection");
            }
            connectionService.decline(connection);
            return ResponseEntity.ok().body("declined");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("wrong connection id");
        }

    }

    @RequestMapping(value = "/get_approved_connections", method = RequestMethod.GET)
    public ResponseEntity getApprovedConnections(Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(connectionService.getApprovedConnections(userInit.get()));
    }

    @RequestMapping(value = "/get_unapproved_connections", method = RequestMethod.GET)
    public ResponseEntity getUnapprovedConnections(Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(connectionService.getUnapprovedConnections(userInit.get()));
    }

    @RequestMapping(value = "/get_sent_connections", method = RequestMethod.GET)
    public ResponseEntity getSentConnections(Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(connectionService.getSentConnections(userInit.get()));
    }
}

package backend.artise.controllers.rest;

import backend.artise.dto.*;
import backend.artise.services.*;
import backend.artise.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RestUserServiceController {
    private final UserService userService;
    private final UserCategoryService userCategoryService;

    private final CategoryService categoryService;
    private final ProfessionService professionService;

    private final UserServiceService userServiceService;


    @RequestMapping(value = "/order_service", method = RequestMethod.POST)
    public ResponseEntity createUserService(@RequestBody @Valid backend.artise.dto.UserService userServiceDto, Principal principal) {
        //check user != other user
        Integer userId = userServiceDto.getService().getUserCategory().getUser().getId();
        Optional<User> userCur = userService.getByNickname(principal.getName());

        if (!userServiceDto.getUser().getId().equals(userCur.get().getId())){
            return ResponseEntity.badRequest().body("wrong user is logined");
        }

        if (userServiceDto.getUser().getId().equals(userId)){
            return ResponseEntity.badRequest().body("user is same as service user");
        }

        return ResponseEntity.ok().body(userServiceService.createUserService(userServiceDto));
    }


    @RequestMapping(value = "/get_unconfirmed_orders", method = RequestMethod.GET)
    public ResponseEntity getUnconfirmedOrders(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getUnconfiremedOrders(userInit.get(), order, sort_by));
    }

    @RequestMapping(value = "/get_unconfirmed_services", method = RequestMethod.GET)
    public ResponseEntity getUnconfirmedServices(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getUnconfiremedServices(userInit.get(), order, sort_by));
    }

    @RequestMapping(value = "/get_approved_orders", method = RequestMethod.GET)
    public ResponseEntity getApprovedOrders(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getApprovedOrders(userInit.get(), order, sort_by));
    }

    @RequestMapping(value = "/get_approved_services", method = RequestMethod.GET)
    public ResponseEntity getApprovedServices(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getApprovedServices(userInit.get(), order, sort_by));
    }

    @RequestMapping(value = "/get_cancelled_orders", method = RequestMethod.GET)
    public ResponseEntity getCancelledOrders(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getCancelledOrders(userInit.get(), order, sort_by));
    }

    @RequestMapping(value = "/get_cancelled_services", method = RequestMethod.GET)
    public ResponseEntity getCancelledServices(@RequestBody Map<String, Object>  map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        Optional<User> userInit = userService.getByNickname(principal.getName());
        return ResponseEntity.ok().body(userServiceService.getCancelledServices(userInit.get(), order, sort_by));
    }


    @RequestMapping(value = "/cancel_order", method = RequestMethod.POST)
    public ResponseEntity cancelOrder(@RequestBody Map<String, Integer> map, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        try{
            backend.artise.dto.UserService userServiceDto = userServiceService.getById(map.get("order_id")).get();

            if(!userServiceDto.getUser().getId().equals(userInit.get().getId()) && !userServiceDto.getService().getUserCategory().getUser().getId().equals(userInit.get().getId())){
                return ResponseEntity.badRequest().body("wrong order for user");
            }
            userServiceService.cancell(userServiceDto);
            return ResponseEntity.ok().body("cancelled");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("wrong order id");
        }
    }

    @RequestMapping(value = "/approve_order", method = RequestMethod.POST)
    public ResponseEntity approveOrder(@RequestBody Map<String, Integer> map, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());

        try{
            backend.artise.dto.UserService userServiceDto = userServiceService.getById(map.get("order_id")).get();

            if(!userServiceDto.getService().getUserCategory().getUser().getId().equals(userInit.get().getId())){
                return ResponseEntity.badRequest().body("wrong user");
            }
            if (userServiceDto.getStatus().equals(1)){
                return ResponseEntity.badRequest().body("order already approved");
            }
            userServiceService.approve(userServiceDto);
            return ResponseEntity.ok().body("approved");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("wrong order id");
        }

    }




}

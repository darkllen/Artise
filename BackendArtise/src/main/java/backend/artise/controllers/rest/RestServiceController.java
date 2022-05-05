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
public class RestServiceController {
    private final UserService userService;
    private final UserCategoryService userCategoryService;

    private final CategoryService categoryService;
    private final ProfessionService professionService;

    private final ServiceService serviceService;


    @RequestMapping(value = "/create_service", method = RequestMethod.POST)
    public ResponseEntity createService(@RequestBody @Valid Service service, Principal principal) {
        //todo check user authorization
//        Optional<User> user = userService.getByNickname(principal.getName());
//
//        if (user.isEmpty()){
//            return ResponseEntity.badRequest().body("wrong user id");
//        }
//        if (userCategoryService.getUserCategoriesByUserIdAndCategoryId(user.get().getId(), service.getUserCategory().getId()).isEmpty()){
//            return ResponseEntity.badRequest().body("wrong user id");
//        }

        return ResponseEntity.ok().body(serviceService.createService(service));
    }

    @RequestMapping(value = "/get_services_for_category", method = RequestMethod.GET)
    public ResponseEntity getServicesForCategory(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Integer category_id = (Integer) map.getOrDefault("category_id", 0);


        Optional<Category> category = categoryService.getById(category_id);
        if (category.isEmpty()){
            return ResponseEntity.badRequest().body("wrong category_id");
        }
        return ResponseEntity.ok().body(serviceService.getServicesForCategory(category.get(), order, sort_by));

    }

    @RequestMapping(value = "/get_services_for_profession", method = RequestMethod.GET)
    public ResponseEntity getServicesForProfession(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Integer profession_id = (Integer) map.getOrDefault("profession_id", 0);


        Optional<Profession> profession = professionService.getById(profession_id);
        if (profession.isEmpty()){
            return ResponseEntity.badRequest().body("wrong profession_id");
        }
        return ResponseEntity.ok().body(serviceService.getServicesForProfession(profession.get(), order, sort_by));

    }

    @RequestMapping(value = "/get_services_for_user", method = RequestMethod.GET)
    public ResponseEntity getServicesForUser(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Integer user_id = (Integer) map.getOrDefault("user_id", 0);


        Optional<User> user = userService.getById(user_id);
        if (user.isEmpty()){
            return ResponseEntity.badRequest().body("wrong user_id");
        }
        return ResponseEntity.ok().body(serviceService.getServicesForUser(user.get(), order, sort_by));

    }

    @RequestMapping(value = "/get_all_services", method = RequestMethod.GET)
    public ResponseEntity getAllServices(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);

        return ResponseEntity.ok().body(serviceService.getAllServices(order, sort_by));

    }

}

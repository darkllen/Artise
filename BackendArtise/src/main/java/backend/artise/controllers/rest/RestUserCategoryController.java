package backend.artise.controllers.rest;

import backend.artise.dto.Category;
import backend.artise.dto.Profession;
import backend.artise.dto.UserCategory;
import backend.artise.services.CategoryService;
import backend.artise.services.ProfessionService;
import backend.artise.services.UserCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
public class RestUserCategoryController {
    private final UserCategoryService service;


    @RequestMapping("/user_category/{id}")
    public ResponseEntity getUserCategory(@PathVariable(value="id") Integer id) {
        Optional<UserCategory> userCategoryOptional = service.getById(id);
        if (userCategoryOptional.isPresent()){
            return ResponseEntity.ok(userCategoryOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("wrong user_category id");
        }
    }

        @RequestMapping(value = "/all_user_categories", method = RequestMethod.GET)
    public List<UserCategory> categoriesByProfession(@RequestParam(name = "user", required = false) Integer userId, @RequestParam(name = "category", required = false) Integer categoryId)
    {
        if(categoryId == null && userId == null)
            return service.getUserCategories();
        else if(categoryId == null)
            return service.getUserCategoriesByUserId(userId);
        else if (userId == null)
            return service.getUserCategoriesByCategoryId(categoryId);
        else
            return service.getUserCategoriesByUserIdAndCategoryId(userId, categoryId);
    }

    @ResponseBody
    @RequestMapping(value = {"/add_user_category"}, method = RequestMethod.POST)
    public ResponseEntity addUserCategory(@RequestBody @Valid UserCategory userCategory){
        //todo user authorization
        UserCategory createdUserCategory = service.addUserCategory(userCategory);
        return ResponseEntity.ok(createdUserCategory);
    }

    @ResponseBody
    @RequestMapping(value = {"/remove_user_category"}, method = RequestMethod.DELETE)
    public ResponseEntity removeUserCategory(@RequestBody Integer userCategoryId){
        //todo user authorization
        try{
            service.removeUserCategoryById(userCategoryId);
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.badRequest().body("UserCategory with id "+userCategoryId+" does not exist");

        }
        return ResponseEntity.ok("UserCategory with id "+userCategoryId+" is deleted");
    }

}
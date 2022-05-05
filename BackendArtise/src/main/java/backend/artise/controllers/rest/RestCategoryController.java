package backend.artise.controllers.rest;

import backend.artise.dto.Category;
import backend.artise.dto.Profession;
import backend.artise.dto.UserCategory;
import backend.artise.services.CategoryService;
import backend.artise.services.ProfessionService;
import lombok.RequiredArgsConstructor;
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
public class RestCategoryController {
    private final CategoryService service;


    @ResponseBody
    @RequestMapping(value = {"/add_category"}, method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestBody @Valid Category category){
        //todo user authorization
        Category createdCategory = service.addCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @RequestMapping(value = "/all_categories", method = RequestMethod.GET)
    public List<Category> categoriesByProfession(@RequestParam(name = "profession", required = false) Integer professionId)
    {
        if(professionId == null)
            return service.getCategories();
        else
            return service.getCategoriesByProfessionId(professionId);
    }

    @RequestMapping("/category/{id}")
    public ResponseEntity getCategory(@PathVariable(value="id") Integer id) {
        Optional<Category> categoryOptional = service.getById(id);
        if (categoryOptional.isPresent()){
            return ResponseEntity.ok(categoryOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("wrong category id");
        }
    }

}
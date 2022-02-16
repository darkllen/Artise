package backend.artise.controllers.rest;

import backend.artise.dto.Category;
import backend.artise.dto.Profession;
import backend.artise.services.CategoryService;
import backend.artise.services.ProfessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
public class RestCategoryController {
    private final CategoryService service;


    @RequestMapping(value = "/all_categories", method = RequestMethod.GET)
    public List<Category> categoriesByProfession(@RequestParam(name = "profession", required = false) Integer professionId)
    {
        if(professionId == null)
            return service.getCategories();
        else
            return service.getCategoriesByProfessionId(professionId);
    }

}
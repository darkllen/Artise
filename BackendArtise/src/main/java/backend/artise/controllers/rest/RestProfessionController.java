package backend.artise.controllers.rest;

import backend.artise.dto.Category;
import backend.artise.dto.Profession;
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
public class RestProfessionController {
    private final ProfessionService service;

    @ResponseBody
    @RequestMapping(value = {"/add_profession"}, method = RequestMethod.POST)
    public ResponseEntity addProfession(@RequestBody @Valid Profession profession){
        //todo user authorization
        Profession createdProfession = service.addProfession(profession);
        return ResponseEntity.ok(createdProfession);
    }

    @RequestMapping(value = "/all_professions", method = RequestMethod.GET)
    public List<Profession> allProfessions()
    {
        return service.getProfessions();
    }

    @RequestMapping("/profession/{id}")
    public ResponseEntity getProfession(@PathVariable(value="id") Integer id) {
        Optional<Profession> professionOptional = service.getById(id);
        if (professionOptional.isPresent()){
            return ResponseEntity.ok(professionOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("wrong profession id");
        }
    }

}
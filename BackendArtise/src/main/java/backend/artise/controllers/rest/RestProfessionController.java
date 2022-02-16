package backend.artise.controllers.rest;

import backend.artise.dto.Profession;
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
public class RestProfessionController {
    private final ProfessionService service;


    @RequestMapping("/all_professions")
    public List<Profession> allProfessions()
    {
        return service.getProfessions();
    }

}
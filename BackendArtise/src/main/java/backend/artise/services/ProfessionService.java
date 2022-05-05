package backend.artise.services;


import backend.artise.dto.Category;
import backend.artise.dto.Profession;
import backend.artise.repos.ProfessionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessionService {

    private final ProfessionRepo repo;

    public List<Profession> getProfessions(){
        return repo.findAll();
    }

    public Optional<Profession> getById(int id){
        return repo.findById(id);
    }

    public void removeProfessionById(Integer id) {
        repo.deleteById(id);
    }

    public Profession addProfession(Profession profession) {
        repo.saveAndFlush(profession);
        return profession;
    }
}

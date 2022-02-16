package backend.artise.services;


import backend.artise.dto.Category;
import backend.artise.repos.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo repo;

    public List<Category> getCategories(){
        return repo.findAll();
    }

    public List<Category> getCategoriesByProfessionId(int professionId){
        return repo.getAllByProfessionId(professionId);
    }


    public Optional<Category> getById(int id){
        return repo.findById(id);
    }

    public void removeCategoryById(Integer id) {
        repo.deleteById(id);
    }
}

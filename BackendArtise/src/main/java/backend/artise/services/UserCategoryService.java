package backend.artise.services;

import backend.artise.dto.Category;
import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import backend.artise.dto.UserLogin;
import backend.artise.repos.UserCategoryRepo;
import backend.artise.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCategoryService {
    private final UserCategoryRepo repo;


    public UserCategory addUserCategory(UserCategory userCategory) {
        repo.saveAndFlush(userCategory);
        return userCategory;
    }

    public List<UserCategory> getUserCategories(){
        return repo.findAll();
    }

    public List<UserCategory> getUserCategoriesByCategoryId(int categoryId){
        return repo.getAllByCategoryId(categoryId);
    }

    public List<UserCategory> getUserCategoriesByUserId(int userId){
        return repo.getAllByUserId(userId);
    }

    public List<UserCategory> getUserCategoriesByUserIdAndCategoryId(int userId, int categoryId){
        return repo.getAllByUserIdAndCategoryId(userId, categoryId);
    }


    public Optional<UserCategory> getById(int id){
        return repo.findById(id);
    }

    public void removeUserCategoryById(Integer id) {
        repo.deleteById(id);
    }

}

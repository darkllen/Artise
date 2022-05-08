package backend.artise.services;

import backend.artise.dto.Category;
import backend.artise.dto.Review;
import backend.artise.dto.User;
import backend.artise.dto.UserCategory;
import backend.artise.repos.CategoryRepo;
import backend.artise.repos.ConnectionRepo;
import backend.artise.repos.ReviewRepo;
import backend.artise.repos.UserCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepo repo;
    private final UserCategoryRepo categoryRepo;

    public Review createReview(Review review) {
        Review r = repo.saveAndFlush(review);
        Float new_r = repo.getAverageRating(r.getUserCategory());
        r.getUserCategory().setRating(new_r);
        return r;
    }

    public List<Review> getUserCategoryReviews(UserCategory category, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAllByUserCategory(category, Sort.by(direction, sort_by));
    }

    public List<Review> getUserReviews(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAllByUser(user, Sort.by(direction, sort_by));
    }

    public double getAvRating(User user){
        return repo.getAvgRatingUser(user);
    }
}

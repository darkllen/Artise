package backend.artise.controllers.rest;

import backend.artise.dto.*;
import backend.artise.services.ConnectionService;
import backend.artise.services.ReviewService;
import backend.artise.services.UserCategoryService;
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
public class RestReviewController {
    private final UserService userService;
    private final ConnectionService connectionService;
    private final ReviewService reviewService;
    private final UserCategoryService userCategoryService;


    @RequestMapping(value = "/create_review", method = RequestMethod.POST)
    public ResponseEntity createReview(@RequestBody @Valid Review review, Principal principal) {
        Optional<User> userInit = userService.getByNickname(principal.getName());
        if (!connectionService.isConnected(userInit.get(), review.getUserCategory().getUser())){
            return ResponseEntity.badRequest().body("not connected");
        }
        return ResponseEntity.ok().body(reviewService.createReview(review));
    }

    @RequestMapping(value = "/get_user_category_reviews", method = RequestMethod.GET)
    public ResponseEntity getUserCategoryReviews(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Integer category_id = (Integer) map.getOrDefault("user_category_id", 0);

        Optional<UserCategory> category = userCategoryService.getById(category_id);
        if (category.isEmpty()){
            return ResponseEntity.badRequest().body("no category");
        }
        return ResponseEntity.ok().body(reviewService.getUserCategoryReviews(category.get(), order, sort_by));

    }

    @RequestMapping(value = "/get_user_reviews", method = RequestMethod.GET)
    public ResponseEntity getUserReviews(@RequestBody Map<String, Object> map) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Integer user_id = (Integer) map.getOrDefault("user_id", 0);

        Optional<User> user = userService.getById(user_id);
        if (user.isEmpty()){
            return ResponseEntity.badRequest().body("no user");
        }
        return ResponseEntity.ok().body(reviewService.getUserReviews(user.get(), order, sort_by));

    }

    @RequestMapping(value = "/get_own_reviews", method = RequestMethod.GET)
    public ResponseEntity getOwnReviews(@RequestBody Map<String, Object> map, Principal principal) {
        String sort_by = (String) map.getOrDefault("sort_by", "");
        Integer order = (Integer) map.getOrDefault("order", 0);
        Optional<User> user = userService.getByNickname(principal.getName());

        return ResponseEntity.ok().body(reviewService.getUserReviews(user.get(), order, sort_by));

    }

}

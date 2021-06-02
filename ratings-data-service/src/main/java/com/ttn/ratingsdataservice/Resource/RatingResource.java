package com.ttn.ratingsdataservice.Resource;

import com.ttn.ratingsdataservice.Model.Rating;
import com.ttn.ratingsdataservice.Model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,8);
    }
    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 3),
                new Rating("456", 6)
        );
        UserRating userRating=new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }

}

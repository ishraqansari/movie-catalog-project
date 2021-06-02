package com.ttn.moviecatalogservice.Resource;

import com.netflix.discovery.converters.Auto;
import com.ttn.moviecatalogservice.Model.CatalogItem;
import com.ttn.moviecatalogservice.Model.Movie;
import com.ttn.moviecatalogservice.Model.Rating;
import com.ttn.moviecatalogservice.Model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId) {

        UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingdata/user/foo",UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
                            Movie.class);
                    return new CatalogItem(movie.getName(), "test", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}

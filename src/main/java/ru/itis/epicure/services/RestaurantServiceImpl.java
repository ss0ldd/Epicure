package ru.itis.epicure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.RestaurantDto;
import ru.itis.epicure.exceptions.RestaurantNotFoundException;
import ru.itis.epicure.models.Restaurant;
import ru.itis.epicure.repository.PostsRepository;
import ru.itis.epicure.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Component
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public RestaurantDto getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId));
        return RestaurantDto.from(restaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurantsByRating() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        restaurants.sort((r1, r2) -> {
                double rating1 = r1.calculateRating();
                double rating2 = r2.calculateRating();
                return Double.compare(rating2, rating1);
                });

        return RestaurantDto.from(restaurants);
    }

    @Override
    public List<RestaurantDto> getRestaurantByName(String name) {
        return RestaurantDto.from(restaurantRepository.findByRestaurantName(name));
    }

    @Override
    public List<PostDto> getRestaurantPosts(Long restaurantId, Long currentUserId) {
        return PostDto.from(postsRepository.findAllByRestaurant_RestaurantId(restaurantId), currentUserId);
    }

    @Override
    public RestaurantDto getRestaurantByNameAndAddress(String restaurantName, String address) {
        Restaurant restaurant = restaurantRepository
                .findByRestaurantNameAndRestaurantAddress(restaurantName, address)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with name: " + restaurantName + " and address: " + address));

        return RestaurantDto.from(restaurant);
    }
}

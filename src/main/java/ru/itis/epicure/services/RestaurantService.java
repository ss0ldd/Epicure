package ru.itis.epicure.services;

import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.RestaurantDto;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    RestaurantDto getRestaurantById(Long restaurantId);
    List<PostDto> getRestaurantPosts(Long restaurantId, Long currentUserId);
    List<RestaurantDto> getRestaurantByName(String name);
    List<RestaurantDto> getAllRestaurantsByRating();
    RestaurantDto getRestaurantByNameAndAddress(String restaurantName, String address);
}

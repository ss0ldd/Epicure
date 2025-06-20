package ru.itis.epicure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.epicure.models.Post;
import ru.itis.epicure.models.Restaurant;
import ru.itis.epicure.models.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private Long restaurantId;
    private String restaurantName;
    private String address;
    private String email;
    private Double rating;
    private String phone;
//    private HashSet<Tag> tags;

    public static RestaurantDto from(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getRestaurantAddress())
                .email(restaurant.getRestaurantEmail())
                .rating(restaurant.calculateRating())
                .phone(restaurant.getRestaurantPhone())
                .build();
    }

    public static List<RestaurantDto> from(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantDto::from)
                .collect(Collectors.toList());
    }
}

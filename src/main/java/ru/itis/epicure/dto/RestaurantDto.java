package ru.itis.epicure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //теги!!
}

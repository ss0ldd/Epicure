package ru.itis.epicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.epicure.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByRestaurantName(String name);
    Restaurant findByRestaurantId(long id);
    Optional<Restaurant> findByRestaurantNameAndRestaurantAddress(String restaurant, String address);
    List<Restaurant> findAll();
}

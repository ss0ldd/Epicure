package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.epicure.dto.RestaurantDto;
import ru.itis.epicure.services.RestaurantService;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String getMainPage(@RequestParam(name = "s", required = false) String restaurantName, Model model) {

        if( restaurantName == null || restaurantName.trim().isEmpty()) {
            List<RestaurantDto> allRestaurants = restaurantService.getAllRestaurantsByRating();
            model.addAttribute("restaurants", allRestaurants);
        } else {
            List<RestaurantDto> searchRestaurants = restaurantService.getRestaurantByName(restaurantName);
            model.addAttribute("restaurants", searchRestaurants);
            model.addAttribute("searchQuery", restaurantName);
        }
        return "main_page";
    }

}

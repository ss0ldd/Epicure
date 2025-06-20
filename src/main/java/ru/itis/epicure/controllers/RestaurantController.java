package ru.itis.epicure.controllers;

import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.PostForm;
import ru.itis.epicure.dto.RestaurantDto;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.models.Restaurant;
import ru.itis.epicure.models.User;
import ru.itis.epicure.security.details.UserDetailsImpl;
import ru.itis.epicure.services.EmailService;
import ru.itis.epicure.services.PostService;
import ru.itis.epicure.services.RestaurantService;

import java.util.List;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PostService postService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurantPage(Model model, @PathVariable("restaurantId") Long restaurantId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        RestaurantDto restaurantProfile = restaurantService.getRestaurantById(restaurantId);
        List<PostDto> restaurantPosts = restaurantService.getRestaurantPosts(restaurantId, userDetails.getUser().getUserId());

        model.addAttribute("restaurant", restaurantProfile);

        model.addAttribute("posts", restaurantPosts);


        return "restaurant_page";
    }

    @PostMapping("/restaurant/{restaurantId}/event")
    public String createEventAndSendEmail(
            @PathVariable Long restaurantId,
            @RequestParam("eventName") String eventName,
            @RequestParam("eventDate") String eventDate,
            @RequestParam("eventTime") String eventTime,
            @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        User user = userDetails.getUser();

        String subject = "Напоминание о мероприятии: " + eventName;
        String text = String.format("""
            Привет, %s!

            Напоминание о мероприятии:
            Название: %s
            Дата: %s
            Время: %s

            Ресторан: %s
            Адрес: %s
            """,
                user.getUsername(), eventName, eventDate, eventTime,
                restaurantService.getRestaurantById(restaurantId).getRestaurantName(),
                restaurantService.getRestaurantById(restaurantId).getAddress()
        );


        System.out.println(user.getEmail());
        emailService.sendEmail(user.getEmail(), subject, text);
        System.out.println("email sent");
        model.addAttribute("emailSent", true);
        return getRestaurantPage(model, restaurantId, userDetails);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public String addPost(@PathVariable Long restaurantId, PostForm postForm,
                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                          RedirectAttributes redirectAttributes) {
        try {
            RestaurantDto restaurant = restaurantService.getRestaurantById(restaurantId);
            User user = userDetails.getUser();

            if (postForm.getFiles() != null && postForm.getFiles().size() > 5) {
                redirectAttributes.addFlashAttribute("error", "Нельзя добавить больше 10 файлов");
                return "redirect:/restaurant/" + restaurantId;
            }
            postForm.setRestaurantId(restaurantId);
            postService.addPost(postForm, user.getUserId(), postForm.getFiles());
            redirectAttributes.addFlashAttribute("success", "Post created successfully!");} catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating post: " + e.getMessage());
        }
        return "redirect:/restaurant/" + restaurantId;

    }

//    @PostMapping("/restaurant/{restaurantId}/delete-post/{postId}")
//    public String deletePost(@PathVariable Long restaurantId,
//                             @PathVariable Long postId,
//                             @AuthenticationPrincipal UserDetailsImpl userDetails,
//                             RedirectAttributes redirectAttributes) {
//        try {
//            User user = userDetails.getUser();
//            postService.deletePost(postId, user.getUserId());
//            redirectAttributes.addFlashAttribute("success", "Post deleted successfully!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Error deleting post: " + e.getMessage());
//        }
//        return "redirect:/restaurant/" + restaurantId;
//    }

}

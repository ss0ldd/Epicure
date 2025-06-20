package ru.itis.epicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.epicure.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_UserId(Long userId);
    List<Post> findAllByRestaurant_RestaurantId(Long restaurantId);
    Optional<Post> getPostByPostId(Long postId);
}

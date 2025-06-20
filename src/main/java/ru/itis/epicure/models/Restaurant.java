package ru.itis.epicure.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String restaurantAddress;

    @Column(unique=true, nullable = false)
    private String restaurantPhone;

    @Column(unique=true, nullable = false)
    private String restaurantEmail;

    @OneToMany(mappedBy = "restaurant")
    private List<Post> posts = new ArrayList<>();

    @Transient
    private Double rating;

    public Double calculateRating() {
        if (posts.isEmpty()) {
            return 0.0;
        }
        double rating = posts.stream().mapToDouble(Post::getRating).sum();
        return rating/posts.size();
    }
}

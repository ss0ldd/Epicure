package ru.itis.epicure.models;

import jakarta.persistence.*;

@Entity
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

    private Double rating; // Average rating

    private String tags; // Tag Tags~
}

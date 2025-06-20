package ru.itis.epicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.epicure.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUserId(Long id);
}

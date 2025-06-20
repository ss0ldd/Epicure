package ru.itis.epicure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.epicure.models.Like;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.user.userId = :userId AND l.post.postId = :postId")
    Optional<Like> findByUserIdAndPostId(@Param("userId") Long userId,
                                         @Param("postId") Long postId);

    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.user.userId = :userId AND l.post.postId = :postId")
    boolean existsByUserIdAndPostId(@Param("userId") Long userId,
                                    @Param("postId") Long postId);

    @Modifying
    @Query("SELECT l FROM Like l WHERE l.post.postId = :postId")
    List<Like> findByPostId(Long postId);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.postId = :postId")
    int countByPostId(@Param("postId") Long postId);
}

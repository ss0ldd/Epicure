package ru.itis.epicure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.epicure.models.FileInfo;
import ru.itis.epicure.models.Like;
import ru.itis.epicure.models.Post;
import ru.itis.epicure.repository.LikesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String restaurantName;
    private String restaurantAddress;
    private Integer rating;
    private Integer likes;
    private boolean isLiked;
    private Long userId;
    private String userName;
    private Date date;
    private List<FileInfoDto> files;

    private LikesRepository likesRepository;

    public static PostDto from(Post post, Long currentUserId) {
        int likes = post.getLikes().size();
        List<FileInfoDto> fileDtos = post.getFiles().stream()
                .map(file -> FileInfoDto.builder()
                        .id(file.getId())
                        .url(file.getUrl())              // самое важное поле!
                        .originalFileName(file.getOriginalFileName())
                        .storageFileName(file.getStorageFileName())
                        .size(file.getSize())
                        .type(file.getType())
                        .build()
                )
                .collect(Collectors.toList());

        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .restaurantName(post.getRestaurant().getRestaurantName())
                .restaurantAddress(post.getRestaurant().getRestaurantAddress())
                .userId(post.getUser().getUserId())
                .userName(post.getUser().getUsername())
                .likes(post.getLikes().size())
                .isLiked(post.isLikedByUser(currentUserId))
                .rating(post.getRating())
                .date(post.getPostDate())
                .files(fileDtos)
                .build();
    }

    public static List<PostDto> from(List<Post> posts, Long currentUserId) {
        List<PostDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(PostDto.from(post, currentUserId));
        }
        return dtos;
    }
}

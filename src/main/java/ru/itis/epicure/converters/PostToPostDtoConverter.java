package ru.itis.epicure.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.models.Post;

@Component
public class PostToPostDtoConverter implements Converter<Post, PostDto> {
    
    @Override
    public PostDto convert(Post post) {
        if (post == null) {
            return null;
        }
        
        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .restaurantName(post.getRestaurant() != null ? post.getRestaurant().getRestaurantName() : null)
                .restaurantAddress(post.getRestaurant() != null ? post.getRestaurant().getRestaurantAddress() : null)
                .userId(post.getUser() != null ? post.getUser().getUserId() : null)
                .userName(post.getUser() != null ? post.getUser().getUsername() : null)
                .likes(post.getLikes() != null ? post.getLikes().size() : 0)
                .isLiked(false) // По умолчанию false, так как нет информации о текущем пользователе
                .rating(post.getRating())
                .date(post.getPostDate())
                .files(post.getFiles() != null ? 
                    post.getFiles().stream()
                        .map(file -> ru.itis.epicure.dto.FileInfoDto.builder()
                            .id(file.getId())
                            .url(file.getUrl())
                            .originalFileName(file.getOriginalFileName())
                            .storageFileName(file.getStorageFileName())
                            .size(file.getSize())
                            .type(file.getType())
                            .build())
                        .collect(java.util.stream.Collectors.toList()) : 
                    new java.util.ArrayList<>())
                .build();
    }
} 
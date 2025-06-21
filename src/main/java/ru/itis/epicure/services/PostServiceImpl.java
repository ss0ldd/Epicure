package ru.itis.epicure.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.PostForm;
import ru.itis.epicure.exceptions.PostNotFoundException;
import ru.itis.epicure.models.*;
import ru.itis.epicure.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private FilesInfoRepository fileInfoRepository;

    @Override
    public List<PostDto> postsByUserId(Long userId, Long currentUserId) {
        return PostDto.from(postsRepository.findAllByUser_UserId(userId), currentUserId);
    }

    @Override
    public PostDto getPostById(Long postId, Long currentUserId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        return PostDto.from(post, currentUserId);
    }

    @Override
    @Transactional
    public void addPost(PostForm postForm, Long userId, List<MultipartFile> files) {
        if (files != null && files.size() > 5) {
            throw new IllegalArgumentException("Можно прикрепить не более 5 файлов");
        }

        User author = usersRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); //////???? в другом месте пофиксить
        Restaurant restaurant = restaurantRepository
                .findByRestaurantId(
                        postForm.getRestaurantId()
                );
        Post post = Post.builder()
                .title(postForm.getTitle())
                .content(postForm.getContent())
                .rating(postForm.getRating())
                .postDate(new Date())
                .user(author)
                .files(new ArrayList<>())
                .restaurant(restaurant)
                .build();
        System.out.println(post);
        postsRepository.save(post);

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    FileInfo fileInfo = fileStorageService.saveFile(file);

                    // безопасное добавление
                    if (fileInfo.getPosts() == null) {
                        fileInfo.setPosts(new ArrayList<>());
                    }
                    fileInfo.getPosts().add(post);

                    if (post.getFiles() == null) {
                        post.setFiles(new ArrayList<>());
                    }
                    post.getFiles().add(fileInfo);

                    fileInfoRepository.save(fileInfo);
                }
            }
        }
    }

    @Override
    @Transactional
    public boolean toggleLikePost(Long postId, Long userId) {
        Post post = postsRepository.getPostByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        User user = usersRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Like> existingLike = likesRepository.findByUserIdAndPostId(userId, postId)
                .stream()
                .findFirst();

        if(existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
            return false; // лайк был удален
        } else {
            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likesRepository.save(like);
            return true; // лайк был добавлен
        }
    }

    @Override
    public int getPostLikeCount(Long postId) {
        return likesRepository.countByPostId(postId); // Лучше использовать countBy
    }

}
